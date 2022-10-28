package com.ramon.jpa.app.service;

import com.ramon.jpa.app.domain.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClienteService {
    List<Cliente> findAll();

    Page<Cliente> findAll(Pageable pageable);

    void save(Cliente cliente);

    Cliente findById(Long id);

    void deleteById(Long id);
}
