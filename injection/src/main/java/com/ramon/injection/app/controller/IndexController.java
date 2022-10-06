package com.ramon.injection.app.controller;

import com.ramon.injection.app.model.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//@Qualifier("[Nombre del componente a inyectar]")
//Funciona para indicar que implementacion del componente se va a inyectar si es que existen mas de una
//Se debe utilizar junto con @Autowired
//Aunque algun componente tenga @Primary esta anotacion se ignora


@Controller
public class IndexController {

    //Inyeccion por atributo
    @Autowired
    @Qualifier("MyServiceBean")
    private IService service;

    //@Autowired Inyeccion por constructor, se puede omitir el @Autowired
//    public IndexController(IService service) {
//        this.service = service;
//    }

    @GetMapping({"", "/", "/index"})
    public String index(Model model) {
        model.addAttribute("result", service.operation());
        return "index";
    }

    //@Autowired Inyeccion por metodo set
//    public void setService(IService service) {
//        this.service = service;
//    }
}
