package com.ramon.interceptores.app.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component("TiempoTranscurridoInterceptor")
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Entrando al metodo preHandle del interceptor");
        logger.info("Interceptando " + handler);

        boolean cumple = true;

        if (cumple) {
            Long tiempoInicio = System.currentTimeMillis();
            request.setAttribute("tiempoInicio", tiempoInicio);

            Random random = new Random();
            Integer demora = random.nextInt(500);
            TimeUnit.MILLISECONDS.sleep(demora);
            return true;
        }
        //Si no se cumple con algo en el interceptor podemos redirigir a otra pagina y devolver false
        response.sendRedirect(request.getContextPath().concat("/login"));
        return false;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);

        Long tiempoFin = System.currentTimeMillis();

        Long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
        Long tiempoTranscurrido = tiempoFin - tiempoInicio;

        if (modelAndView != null) {
            modelAndView.addObject("tiempoTranscurrido", tiempoTranscurrido);
        }
        logger.info("Tiempo transcurrido: " + tiempoTranscurrido + " ms");
        logger.info("Saliendo al metodo postHandle del interceptor");
    }
}
