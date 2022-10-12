package com.ramon.forms.app.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = IdentificadorRegexValidator.class)
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface IdentificadorRegex {
    String message() default "Identificador sin formato valido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
