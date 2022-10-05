package com.ramon.mvc.app.controller;

import com.ramon.mvc.app.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/app")//Ruta de primer nivel para acceder a los metodos
public class IndexController {

    //Con @Value inyectamos los valores que se encuentren en algun archivo de propiedades, por defecto esta application
    @Value("${indexcontroller.titulo}")
    private String titulo;

    @Value("${indexcontroller.perfil}")
    private String perfil;

    @Value("${indexcontroller.listado}")
    private String listado;

    //@RequestMapping //por default es get o se puede indicar en su atributo method
    //@GetMapping es un @RequestMapping con el metodo GET

    //Esta mapeado a tres posibles rutas
    //http://localhost:8080/app/index, http://localhost:8080/app/, http://localhost:8080/app/home
    @GetMapping({"/index", "/", "home"})
    public String index(Model model) {
        //ModelAndView, ModelMap, Model o Map nos ayudan a pasar atributos del controlador a la vista
        model.addAttribute("titulo", titulo);
        return "index";//Apunta al /resources/templates/index.html
    }

    @GetMapping("/perfil")
    public String perfil(Model model) {

        Usuario usuario = new Usuario();
        usuario.setApellido("Perez");
        usuario.setNombre("Ramon");
        usuario.setEmail("ramon@hotmail.com");

        model.addAttribute("titulo", perfil);
        model.addAttribute("usuario", usuario);

        return "perfil";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("titulo", listado);
        return "listar";
    }

    //ModelAttribute permite pasar informacion a la vista por medio de un metodo
    //esta informacion es global para todos los metodos del controlador
    //el nombre del objeto a pasar en este caso es "usuarios"
    @ModelAttribute("usuarios")
    public List<Usuario> getUsuarios() {
        return Arrays.asList(
                new Usuario("Ramon", "Perez", "ramon@hotmail.com"),
                new Usuario("Juan", "Morales", "juan@hotmail.com"),
                new Usuario("Jose", "Ruiz", "jose@hotmail.com")
        );
    }
}
