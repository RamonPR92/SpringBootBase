package com.ramon.mvc.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource("classpath:titulos.properties")
        //Se pueden agregar mas archivos de propiedades aqui para cargarlos
})
public class PropertiesConfig {
}
