package com.ramon.interceptores.app.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component("HorarioInterceptor")
public class HorarioInterceptor implements HandlerInterceptor {

    @Value("${config.horario.apertura}")
    private Integer horaApertura;

    @Value("${config.horario.cierre}")
    private Integer horaCierre;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        LocalTime now = LocalTime.now();
        LocalTime horaAperturaLocalTime = LocalTime.of(horaApertura, 0);
        LocalTime horaCierreLocalTime = LocalTime.of(horaCierre, 0);

        if(now.isBefore(horaAperturaLocalTime) || now.isAfter(horaCierreLocalTime)){
            response.sendRedirect(request.getContextPath().concat("/cerrado"));
            return false;
        }
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Bienvenidos al horario de atencion a clientes ");
        mensaje.append("Atendemos de las ");
        mensaje.append(horaAperturaLocalTime.format(DateTimeFormatter.ISO_LOCAL_TIME));
        mensaje.append(" a las ");
        mensaje.append(horaCierreLocalTime.format(DateTimeFormatter.ISO_LOCAL_TIME));
        mensaje.append(" Gracias por su visita");

        request.setAttribute("mensaje", mensaje.toString());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String mensaje = (String)request.getAttribute("mensaje");
        modelAndView.addObject( "horario" ,mensaje);
    }
}
