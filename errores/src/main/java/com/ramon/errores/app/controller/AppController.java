package com.ramon.errores.app.controller;

import com.ramon.errores.app.domain.Usuario;
import com.ramon.errores.app.service.UsuarioNoEncontradoException;
import com.ramon.errores.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AppController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/index")
    public String index(){
//        Integer resultado = 100 /0;
        Integer.parseInt("34jj");
        return "index";
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Integer id, Model model) throws UsuarioNoEncontradoException {
        Usuario usuario = usuarioService.obtenerPorId(id);
        model.addAttribute("titulo", "Detalle de usuario ".concat(usuario.getNombre()));
        model.addAttribute("usuario", usuario);
        return "ver";
    }
}
