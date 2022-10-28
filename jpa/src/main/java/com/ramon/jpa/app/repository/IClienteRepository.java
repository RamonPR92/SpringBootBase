package com.ramon.jpa.app.repository;

import com.ramon.jpa.app.domain.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

//Ejemplo con CrudRepository
//Ya cuenta con una variedad de metodos que podemos utilizar
public interface IClienteRepository extends PagingAndSortingRepository<Cliente, Long> {

}
