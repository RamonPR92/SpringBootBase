package com.ramon.injection.app.model.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

//Cuando existen mas de una implementacion se debe de anotar con @Primary la implementacion que
//deseemos que sea inyectada por el contenedor de spring
//@Primary
@Service("MyServiceComplex")
public class MyServiceComplex implements IService {

    @Override
    public String operation() {
        return "executing a complex service operation ...";
    }
}
