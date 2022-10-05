package com.ramon.mvc.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("params")
public class ParamsController {

    @GetMapping("/")
    public String index(){
        return "params/index";
    }

    //@RequestParam("texto") String otroTexto
    //El nombre del parametro se puede indicar dentro de la anotacion como value
    //o en el nombre del parametro del metodo String texto
    //defaultValue es el valor por defecto cuando no se manda este parametro
    //required indica si el parametro es obligatorio o no, por defecto es obligatorio
    //http://localhost:8080/params/one-param?texto=valor
    @GetMapping("/one-param")
    public String param(@RequestParam(value = "saludo", defaultValue = "vacio", required = false) String saludo,  Model model){
        model.addAttribute("resultado", "El parametro enviado por la url es: " + saludo);
        return "params/ver";
    }

    //http://localhost:8080/params//more-params?saludo=Hola&numero=30&decimal=4.5
    @GetMapping("/more-params")
    public String param(@RequestParam String saludo, @RequestParam Integer numero, @RequestParam Double decimal, Model model){
        model.addAttribute("resultado", "Saludos: " + saludo + " numero: " + numero+ " decimal: " + decimal);
        return "/params/ver";
    }

    //Se pueden tratar los parametros por separado, pero tambien pueden obtenerse del HttpServletRequest
    //http://localhost:8080/params//more-params?saludo=Hola&numero=30&decimal=4.5
    @GetMapping("/more-params-request")
    public String param(HttpServletRequest request, Model model){
        String saludo = request.getParameter("saludo");
        Integer numero = Integer.parseInt(request.getParameter("numero"));
        Double decimal = Double.parseDouble(request.getParameter("decimal"));
        model.addAttribute("resultado", "Saludos: " + saludo + " numero: " + numero+ " decimal: " + decimal);
        return "/params/ver";
    }

}
