package com.ramon.interceptores.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {

    /**
     * Se agrega el interceptor a la configuracion del interceptor,
     * En el metodo addPathPatterns se pueden agregar patrones de rutas en las que va a aplicar el
     * interceptor y tambien podemos excluir rutas, por default se aplica a todas las rutas
     */

    @Autowired
    @Qualifier("TiempoTranscurridoInterceptor")
    private HandlerInterceptor tiempoTranscurridoInterceptor;

    @Autowired
    @Qualifier("HorarioInterceptor")
    private HandlerInterceptor horaroInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tiempoTranscurridoInterceptor).addPathPatterns("/index");
        registry.addInterceptor(horaroInterceptor).addPathPatterns("/index");
    }
}
