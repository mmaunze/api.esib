package com.esib.esib.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import static io.jsonwebtoken.Jwts.builder;
import static io.jsonwebtoken.Jwts.parserBuilder;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;
import io.jsonwebtoken.security.SignatureException;
import static java.lang.System.currentTimeMillis;
import java.util.Date;
import static java.util.Objects.nonNull;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Meldo Maunze
 */
@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     *
     * @param username
     * @return
     */
    public String generateToken(String username) {
        var key = getKeyBySecret();
        return builder()
                .setSubject(username)
                .setExpiration(new Date(currentTimeMillis() + this.expiration))
                .signWith(key)
                .compact();
    }

    private SecretKey getKeyBySecret() {
        var key = hmacShaKeyFor(this.secret.getBytes());
        return key;
    }

    /**
     *
     * @param token
     * @return
     */
    public boolean isValidToken(String token) {
        var claims = getClaims(token);
        if (nonNull(claims)) {
            var username = claims.getSubject();
            var expirationDate = claims.getExpiration();
            var now = new Date(currentTimeMillis());
            if (nonNull(username) && nonNull(expirationDate) && now.before(expirationDate)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param token
     * @return
     */
    public String getUsername(String token) {
        var claims = getClaims(token);
        if (nonNull(claims)) {
            return claims.getSubject();
        }
        return null;
    }

    private Claims getClaims(String token) {
        var key = getKeyBySecret();
        try {
            return parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | SignatureException | IllegalArgumentException e) {
            return null;
        }
    }
    private static final Logger LOG = Logger.getLogger(JWTUtil.class.getName());

}