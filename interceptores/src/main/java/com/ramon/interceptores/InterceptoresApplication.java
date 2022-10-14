package com.ramon.interceptores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InterceptoresApplication {

	/**
	 *
	 * Los interceptores son acciones que se ejecutan antes o despues de una peticion
	 * Sus usos son:
	 * 	Validacion de datos
	 * 	Manejo de transacciones SQL
	 * 	Autenticacion
	 * 	Autorizacion
	 *	Realizar calculos
	 *	Monotoreo y estadisticas del sitio
	 */
	public static void main(String[] args) {
		SpringApplication.run(InterceptoresApplication.class, args);
	}

}
