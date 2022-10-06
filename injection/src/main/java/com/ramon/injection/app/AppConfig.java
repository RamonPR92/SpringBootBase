package com.ramon.injection.app;

import com.ramon.injection.app.model.entity.ItemFactura;
import com.ramon.injection.app.model.entity.Producto;
import com.ramon.injection.app.model.service.IService;
import com.ramon.injection.app.model.service.MyService;
import com.ramon.injection.app.model.service.MyServiceComplex;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AppConfig {

    //@Bean es lo mismo a un componente, y se esta declarando otra forma de proveer objetos para inyectar
    //tal y como funciona el contenedor de inyeccion
    //tambien se puede marcar como @Primary

    //Se recomienda esta forma para registrar clases de terceros, otros frameworks o apis
    @Bean("MyServiceBean")
    public IService registerService(){
        return new MyService();
    }

    @Primary
    @Bean("MyServiceComplexBean")
    public IService registerServiceComplex(){
        return new MyServiceComplex();
    }

    @Bean("ItemsFacturaOficina")
    public List<ItemFactura> registrarItemsOficina(){
        return Arrays.asList(
                new ItemFactura(new Producto("Teclado", 2000.00F), 1),
                new ItemFactura(new Producto("Monitor", 3500.90F), 2),
                new ItemFactura(new Producto("Mouse", 1000.00F), 1),
                new ItemFactura(new Producto("Audifonos", 3000.50F), 1)
        );
    }

    @Bean("ItemsFacturaDeportes")
    public List<ItemFactura> registrarItemsDeportes(){
        return Arrays.asList(
                new ItemFactura(new Producto("Balon", 500.00F), 3),
                new ItemFactura(new Producto("Guantes", 700.90F), 2),
                new ItemFactura(new Producto("Porteria", 1000.00F), 2),
                new ItemFactura(new Producto("Zapatos", 2000.50F), 22)
        );
    }
}
