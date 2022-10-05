package com.ramon.mvc.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("path-variable")
public class PathVariableController {

    @GetMapping("/")
    public String index(){
        return "/variable/index";
    }

    //@GetMapping("/string/{texto}") la variable en el path llamada texto
    // debe coincidir con el parametro del metodo @PathVariable String texto
    //http://localhost:8080/path-variable/string/valor variable
    @GetMapping("/string/{texto}")
    public String variables(@PathVariable String texto, Model model){
        model.addAttribute("resultado", "Este es el resultado del path variable " + texto);
        return "/variable/ver";
    }

    @GetMapping("/string/{texto}/{numero}")
    public String variables(@PathVariable String texto, @PathVariable Integer numero, Model model){
        model.addAttribute("resultado", "Este es el resultado del path variable " + texto + " y numero " + numero);
        return "/variable/ver";
    }
}
