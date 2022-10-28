package com.ramon.jpa.app.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "clientes")//El nombre del tabla
public class Cliente implements Serializable {

    @Id//Indica que este campo servira como el id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Establece la estrategia para la generacion del id
    private Long id;

    //@Column(name = "nombreCliente") Si se omite se toma el nombre del campo como el nombre de la columna
    //@Column(nullable = false)
    @NotEmpty
    private String nombre;

//    @Column(nullable = false)//Indica que es NOT NULL
    @NotEmpty
    private String apellido;

    //@Column(nullable = false)
    @Email
    @NotEmpty
    private String email;

    @Column(nullable = false, name = "creado_en")
    @Temporal(TemporalType.DATE)//Hace la conversion de un LocalDateTime y almacena solo la fecha
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date creadoEn;

    private String foto;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    //Es llamado antes del metodo persist del entityManager
//    @PrePersist
//    public void prePersist(){
//        creadoEn = new Date();
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Date creadoEn) {
        this.creadoEn = creadoEn;
    }
}
