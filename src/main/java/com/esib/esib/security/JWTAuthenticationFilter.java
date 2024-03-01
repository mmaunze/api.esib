package com.esib.esib.security;

import com.esib.esib.exceptions.GlobalExceptionHandler;
import com.esib.esib.modelo.Utilizador;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author Meldo Maunze
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(JWTAuthenticationFilter.class.getName());

    /**
     *
     */
    private final AuthenticationManager authenticationManager;

    /**
     *
     */
    private final JWTUtil jwtUtil;

    /**
     *
     * @param authenticationManager
     * @param jwtUtil
     */
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        setAuthenticationFailureHandler(new GlobalExceptionHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    /**
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        try {
            var userCredentials = new ObjectMapper().readValue(request.getInputStream(), Utilizador.class);
            var authToken = new UsernamePasswordAuthenticationToken(
                    userCredentials.getUsername(), userCredentials.getPassword(), new ArrayList<>());
            var authentication = this.authenticationManager.authenticate(authToken);
            return authentication;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param request
     * @param response
     * @param filterChain
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain, Authentication authentication)
            throws IOException, ServletException {
        var userSpringSecurity = (UserSpringSecurity) authentication.getPrincipal();
        String username = userSpringSecurity.getUsername();
        var token = this.jwtUtil.generateToken(username);
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
    }


}