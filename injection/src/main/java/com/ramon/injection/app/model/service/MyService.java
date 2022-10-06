package com.ramon.injection.app.model.service;

import org.springframework.stereotype.Service;

//utilizando @Component o alguno de sus derivados, estamos haciendo que se registre el componente en el
//contenedor de componentes y poder hacer inyecciones cuando queramos una instancia de esta clase
@Service("MyService")
public class MyService implements  IService{

    @Override
    public String operation(){
        return "executing a important process ...";
    }
}
