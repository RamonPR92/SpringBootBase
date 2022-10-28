package com.ramon.jpa.app.controller;

import com.ramon.jpa.app.domain.Cliente;
import com.ramon.jpa.app.service.IClienteService;
import com.ramon.jpa.app.service.IUploadFileService;
import com.ramon.jpa.app.util.paginator.PageRender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;

@Controller
@SessionAttributes("cliente")
//Mantiene el objeto en la sesion almacenado, hasta que llamemos a SessionStatus.setComplete
public class ClienteController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IClienteService iClienteService;

    @Autowired
    private IUploadFileService iUploadFileService;

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable("id") Long id, Model model) {
        Cliente cliente = iClienteService.findById(id);
        if (cliente == null) {
            return "redirect:/listar";
        }
        model.addAttribute("titulo", "Detalle de cliente");
        model.addAttribute("cliente", cliente);
        return "ver";
    }

    @GetMapping("/listar")
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

        //Generamos el paginador con el numero de pagina y el tamamio
        //Una pagina es como una lista de filas
        Pageable pageRequest = PageRequest.of(page, 10);
        Page<Cliente> clientes = iClienteService.findAll(pageRequest);
        PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes", clientes);
        model.addAttribute("page", pageRender);
        return "listar";
    }

    @GetMapping("/form")
    public String crear(Model model) {
        model.addAttribute("titulo", "Formulario de clientes");
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "form";
    }

    @PostMapping("/form")
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, @RequestParam("file") MultipartFile foto, SessionStatus sessionStatus) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de clientes");
            model.addAttribute("cliente", cliente);
            return "/form";
        }
        if (!foto.isEmpty()) {
            String fotoName = null;
            try {
                fotoName = iUploadFileService.copy(foto);
            } catch (IOException e) {
                e.printStackTrace();
            }
            cliente.setFoto(fotoName);
        }
        iClienteService.save(cliente);
        sessionStatus.setComplete();
        return "redirect:/listar";
    }

    //Carga el recurso a mano si no se especifica la ruta de donde conseguirlo en el MVCConfig
    @GetMapping("/uploads/{filename:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
        Resource resource = null;
        try {
            resource = iUploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Cliente cliente = null;
        if (id > 0) {
            cliente = iClienteService.findById(id);
        } else {
            return "redirect:/listar";
        }
        model.addAttribute("titulo", "Editar cliente");
        model.addAttribute("cliente", cliente);
        return "form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, Model model) {
        if (id > 0) {
            Cliente cliente = iClienteService.findById(id);
            iClienteService.deleteById(id);
            //Elimina la foto de la carpeta
            try {
                iUploadFileService.delete(cliente.getFoto());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/listar";
    }
}
