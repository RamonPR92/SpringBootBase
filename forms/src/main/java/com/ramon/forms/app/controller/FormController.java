package com.ramon.forms.app.controller;

import com.ramon.forms.app.editors.PaisPropertyEditor;
import com.ramon.forms.app.editors.RolePropertyEditor;
import com.ramon.forms.app.model.Pais;
import com.ramon.forms.app.model.Role;
import com.ramon.forms.app.model.Usuario;
import com.ramon.forms.app.services.PaisService;
import com.ramon.forms.app.services.RoleService;
import com.ramon.forms.app.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

//@SessionAttributes("usuarios") Mantiene la informacion del usuario durante la sesion,
//Cuando se pasa de una pagina a otra, se esta enviando un nuevo objeto de una a otra,
//Si algun atributo no se mapea en y lo queremos mantener entre peticiones debemos de agregar la anotacion
// y agregar el nombre del objeto que va estar viajando entre peticiones
@Controller
@RequestMapping("")
@SessionAttributes("usuario")
public class FormController {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private PaisService paisService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PaisPropertyEditor paisPropertyEditor;

    @Autowired
    private RolePropertyEditor rolePropertyEditor;

    //Con WebDataBinder al establecer un validador, ya no tendremos que hacer de forma explicita la validacion
    //usando el userValidator
    @InitBinder
    public void initBinder(WebDataBinder binder){
        //Se pueden agregar varios validadores
        binder.addValidators(userValidator);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);//establece lo estricto del formato
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
        binder.registerCustomEditor(Pais.class, "pais", paisPropertyEditor);
        binder.registerCustomEditor(Role.class, "roles", rolePropertyEditor);
    }

    @GetMapping("/form")
    public String form(Model model){
        model.addAttribute("titulo", "Envio de formulario");
        //Si es la primer vez que se llama, debera de contener por lo menos a un usuario aunque no tenga datos
        Usuario user = new Usuario();
        //Pasar datos por default a los input
        user.setIdentificador("9304G");
        user.setNombre("Ramon");
        user.setApellido("Perez");
        user.setHabilitar(true);
        user.setValorSecreto("Secreto");
        model.addAttribute("usuario", user);
        return "form";
    }

    //El User pasado como parametro es inyectado por la peticion
    //Solo se deben de tener los campos  de User con el mismo nombre que en el form del index
    // para que spring los pueda mapear, sin el uso de @RequestParam

    //@Valid Es para hacer validaciones al Objeto
    //En la clase del objeto agregamos las validaciones que haremos a cada campo
    //Por ejemplo @NotEmpty o @Email

    //BindingResult Contiene toda la informacion del formulario, como cuando no pasa las validaciones y tiene errores
    //El BindingResult siempre debe de ir despues del objeto que se valida

    //Con @Valid @ModelAttribute("nuevoNombre")Usuario usuario podemos cambiar el nombre por defecto de usuario
    //esto para cuando lo pasemos implicitamente y que no use el nombre por defecto que seria el de la clase
    @PostMapping("/form")
    public String procesarFormulario(@Valid Usuario usuario, BindingResult bindingResult, Model model){
        //Se valida al usuario por medio de una validacion personalizada
        //en este caso solo valida el password
        //No se usa explicitamente por el WebDataBinder por eso se comenta esta linea
       // userValidator.validate(usuario, bindingResult);


        if(bindingResult.hasErrors()){
            model.addAttribute("titulo", "Resultado del formulario");
            //Se puede omitir el mapeo de errores ya que por defecto se envian al formulario
//            Map<String, String> errores = new HashMap<>();
//            bindingResult.getFieldErrors().forEach(error ->
//                errores.put(error.getField(), error.getDefaultMessage())
//            );
//            //Conseguimos los errores del BindingResult y los devolvemos a la vista
//            model.addAttribute("error", errores);
//            //se puede omitir ya que por defecto se devuelve,
//            //Pero th:object="${usuario}" debe tener el mismo nombre que la clase, osea Usuario
            //model.addAttribute("usuario", usuario);
            return "form";
        }
        //Retornamos el estado del usuario ya con datos validos
        //model.addAttribute("usuario", usuario);
        return "redirect:/ver";
    }

    @GetMapping("/ver")
    public String ver(@SessionAttribute(name = "usuario", required = false) Usuario usuario, Model model, SessionStatus status ){

        if(usuario == null){
            return "redirect:/form";
        }
        model.addAttribute("titulo", "Resultado del formulario");
        //Se elimina el objeto usuario de la sesion, en este punto ya no necesitamos el objeto
        status.setComplete();
        return "resultado";
    }

//    @PostMapping("/form")
//    public String procesarFormulario(Model model, @RequestParam String username,
//                                     @RequestParam String password, @RequestParam String email){
//
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setEmail(email);
//
//        model.addAttribute("titulo", "Resultado del formulario");
//        model.addAttribute("usuario", user);
//
//        return "resultado";
//    }

    @ModelAttribute("paises")
    public List<String> getPaises(){
        return Arrays.asList("Mexico", "EUA", "Colombia", "Chile", "Brasil", "Argentina");
    }

    @ModelAttribute("paisesmap")
    public Map<String, String> getPaisesMap(){
        Map<String, String> paises = new HashMap<>();
        paises.put("MX", "Mexico");
        paises.put("EUA", "Estado Unidos");
        paises.put("COL", "Colombia");
        paises.put("CL", "Chile");
        paises.put("BR", "Brasil");
        paises.put("AR", "Argentina");
     return paises;
    }

    @ModelAttribute("listaPaises")
    public List<Pais> getListaPaises(){
        return paisService.listar();
    }

//    @ModelAttribute("listaRoles")
//    public List<String> listaRoles(){
//        return Arrays.asList(
//                "ROLE_ADMIN",
//                "ROLE_USER",
//                "ROLE_MODERATOR"
//                );
//    }

    @ModelAttribute("listaRoles")
    public List<Role> listaRoles(){
        return roleService.listar();
    }

    @ModelAttribute("listaRolesMap")
    public Map<String, String> listaRolesMap(){
        Map<String, String> roles = new HashMap<>();
        roles.put("ROLE_ADMIN", "Administrador");
        roles.put("ROLE_USER", "Usuario");
        roles.put("ROLE_MODERATOR", "Moderador");
        return roles;
    }
}
