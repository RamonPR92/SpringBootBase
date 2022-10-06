package com.ramon.injection.app.model.entity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.Serializable;
import java.util.List;

//Por defecto el Scope de un componente es Singleton y solo existe un componente unico por la aplicacion
//@RequestScope cambia el scope hace que el componente sea unico por peticion, se inicia o finaliza cuando se recarga el navegador o se cierra
//@SesionScope el scope es unico por session, es recomendado como para carritos de compra, bancos
//Finaliza cuando se cierra la sesion, hay un timeout o cuando se cierra el navegador
//Necesita implementar Serializable
//@ApplicationScope Es parecido a Singleton
@Component
@RequestScope
public class Factura implements Serializable {

    @Value("${factura.descripcion}")
    private String descripcion;

    @Autowired
    private Cliente cliente;

    @Qualifier("ItemsFacturaDeportes")
    @Autowired
    private List<ItemFactura> items;

    //Se ejecuta despues de que sea inyectada la instancia Factura
    @PostConstruct
    public void inicializar(){
        cliente.setNombre("Juan");
        cliente.setApellido("Juarez");
        descripcion = "Factura de deportes";
    }

    //Se ejecuta antes de que el contenedor destruya la instancia Factura
    @PreDestroy
    public void antesDeDestruir(){
        System.out.println("Factura destruida con la descripcion: " + descripcion);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemFactura> getItems() {
        return items;
    }

    public void setItems(List<ItemFactura> items) {
        this.items = items;
    }
}
