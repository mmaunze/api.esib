package com.esib.esib.config;

import com.esib.esib.security.JWTAuthenticationFilter;
import com.esib.esib.security.JWTAuthorizationFilter;
import com.esib.esib.security.JWTUtil;
import java.util.Arrays;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 *
 * @author Meldo Maunze
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    /**
     *
     */
    private static final String[] PUBLIC_MATCHERS = {
        "/",
    };
    /**
     *
     */
    private static final String[] PUBLIC_MATCHERS_POST = {
        "/utilizadores",
        "/login"
    };
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(SecurityConfig.class.getName());

    /**
     *
     */
    private AuthenticationManager authenticationManager;

    /**
     *
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     *
     */
    @Autowired
    private JWTUtil jwtUtil;


    /**
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable();

        var authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(this.userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
        this.authenticationManager = authenticationManagerBuilder.build();

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .anyRequest().authenticated().and()
                .authenticationManager(authenticationManager);

        http.addFilter(new JWTAuthenticationFilter(this.authenticationManager, this.jwtUtil));
        http.addFilter(new JWTAuthorizationFilter(this.authenticationManager, this.jwtUtil,
                this.userDetailsService));

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    /**
     *
     * @return
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        var configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE"));
        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
}
