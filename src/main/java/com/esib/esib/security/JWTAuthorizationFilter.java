package com.esib.esib.security;

import java.io.IOException;
import static java.util.Objects.nonNull;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 *
 * @author Meldo Maunze
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(JWTAuthorizationFilter.class.getName());

    /**
     *
     */
    private final JWTUtil jwtUtil;

    /**
     *
     */
    private final UserDetailsService userDetailsService;

    /**
     *
     * @param authenticationManager
     * @param jwtUtil
     * @param userDetailsService
     */
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil,
            UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    /**
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        var authorizationHeader = request.getHeader("Authorization");
        if (nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            var token = authorizationHeader.substring(7);
            var auth = getAuthentication(token);
            if (nonNull(auth)) {
                getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     *
     * @param token
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if (this.jwtUtil.isValidToken(token)) {
            var username = this.jwtUtil.getUsername(token);
            var user = this.userDetailsService.loadUserByUsername(username);
            var authenticatedUser
                    = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            return authenticatedUser;
        }
        return null;
    }

}
