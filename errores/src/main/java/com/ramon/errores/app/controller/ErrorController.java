package com.ramon.errores.app.controller;

import com.ramon.errores.app.service.UsuarioNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Por medio de vistas con el numero de error como nombre por ejemplo 404.html o 500.html
 * se pueden atender estos errores
 * Se puede crear un controlador especial para las excepciones y presentar una vista especial
 * Tambien
 */

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(ArithmeticException.class)
    public String aritmeticaError(ArithmeticException ex, Model model){
        model.addAttribute("error", "Error de aritmetica");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return "error/aritmetica";
    }

    @ExceptionHandler(NumberFormatException.class)
    public String numberFormat(NumberFormatException ex, Model model){
        model.addAttribute("error", "Error de formato de numero");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return "error/formatoNumero";
    }

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public String usuarioNoEncontrado(UsuarioNoEncontradoException ex, Model model){
        model.addAttribute("error", "Usuario no encontrado");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return "error/formatoNumero";
    }
}
