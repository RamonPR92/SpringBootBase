package com.ramon.forms.app.validation;

import com.ramon.forms.app.model.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

//Podemos validar desde una clase de validaciones especial para una determinda clase, en este caso Usuario
//Tenemos que anotarla como component para que spring inyecte la dependencia
@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Usuario.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Usuario usuario = (Usuario) target;
        //Tenemos metodos que ya hacen una determinada validacion
        ValidationUtils.rejectIfEmpty(errors, "password", "NotEmpty.usuario.password");

        //Podemos hacer otro tipo de validaciones como la expresion regular
        //o cualquier otra
        //El errorCode es el mensaje que se mostrara, es tomado de messages.properties
//        if(!usuario.getPassword().matches("[0-9]{2}[.][\\d]{3}[.][\\d][-][A-Z]{1}")){
//            errors.rejectValue("password", "Regex.usuario.password");
//        }
    }
}
