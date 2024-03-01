package com.esib.esib.config;

import java.util.logging.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author Meldo Maunze
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {


    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(WebConfig.class.getName());
    /**
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
    }

}