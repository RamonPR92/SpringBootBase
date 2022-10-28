package com.ramon.jpa.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class MVCConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//        //Configuramos una ruta con un alias para que el sistema conoce
//        //Pero se traduce a una ruta real en la pc
//        String resourcePath = Paths.get("uploads").toAbsolutePath().toUri().toString();
//        registry.addResourceHandler("/uploads/**")
////                .addResourceLocations("file:/C:/Temp/uploads/");
//                .addResourceLocations(resourcePath);
    }
}
