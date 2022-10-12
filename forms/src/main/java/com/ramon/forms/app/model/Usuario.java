package com.ramon.forms.app.model;

import com.ramon.forms.app.validation.Requerido;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * Los campos pueden ser anotados con validaciones,
 *
 * @NotEmpty es para validar que un campo no este vacio
 * @Email para validar que el campo cumpla con la expresion regular de un correo
 * @Size para validar el minimo o maximo de un campo
 * Estas validaciones se hacen cuando en una peticion se pasan datos y se trabaja con esta clase,
 * de no ser validos, se inyectara en un map con los errores a la siguiente peticion a donde vallan estos datos
 * <p>
 * Se pueden personalizar los mensajes de error cuando no se cumpla alguna validacion  @NotEmpty(message="El campo no debe estar vacio")
 */
public class Usuario {

    //Anotacion personalizada, hecha desde cero
    //@IdentificadorRegex
    private String identificador;

    @NotEmpty
    private String nombre;

    @Requerido
    private String apellido;

    @NotEmpty
    @Size(min = 3, max = 10)
    private String username;

    @Min(2)
    @Max(500)
    @NotNull
    private Integer cuenta;

    //El @DateTimeFormat(pattern = "yyyy-MM-dd") es incorrecto si no se pone asi cuando se usa el input date de html5
    //@Past verfica que la fecha sea en pasado si no lanza un error, tambien se usa @Future
    @NotNull
    //@Past
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;


    //Podemos agregar validaciones con expresiones regulares
    //La expresion se lee
    /**
     * [0-9] y [\\d] indican Cualquier numero del 0 al 9
     * {Numero} Inidcan las repeticiones y va despues de algo en []
     * [caracter] Indica que lleva un caracter en especifico
     * [A-Z] Cualquier letra mayuscula de la A a la Z
     * [a-z] Cualquier letra minuscula de la A a la Z
     * [aA-Zz] Cualquier letra mayuscula o minuscula de la A a la Z
     */
    //@NotEmpty
    //@Pattern(regexp = "[0-9]{2}[.][\\d]{3}[.][\\d][-][A-Z]{1}")
    private String password;

    @NotEmpty
    @Email(message = "No cumple con el formato de correo \"nombre@dominio\"")
    private String email;

    @NotNull
    private Pais pais;

    @NotEmpty
    private List<Role> roles;

    private Boolean habilitar;

    @NotEmpty
    private String genero;

    private String valorSecreto;

    public String getValorSecreto() {
        return valorSecreto;
    }

    public void setValorSecreto(String valorSecreto) {
        this.valorSecreto = valorSecreto;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Integer getCuenta() {
        return cuenta;
    }

    public void setCuenta(Integer cuenta) {
        this.cuenta = cuenta;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Boolean getHabilitar() {
        return habilitar;
    }

    public void setHabilitar(Boolean habilitar) {
        this.habilitar = habilitar;
    }
}
