package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//me peta el cors necesito añadir esta clase para arreglarlo
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        corsRegistry.addMapping("/**")//para todas las rutas
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("POST","GET")//de momento  solo estoy usando el post para el registro y login
                .allowedHeaders("*")
                .allowCredentials(true);


    }


}
