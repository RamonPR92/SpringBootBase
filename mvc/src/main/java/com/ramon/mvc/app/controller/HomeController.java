package com.ramon.mvc.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Con "redirect:/app/index" redirecciona a una pagina local o externa, la peticion es reiniciada
    // Con "forward:/app/index" redirecciona solo a paginas locales y la peticion no es reiniciada
    @GetMapping("/")
    public String home(){
        return "redirect:/app/index";
//        return "redirect:https://www.google.com/";
//        return "forward:/app/index";
    }
}
