package com.ramon.errores.app.service;

import com.ramon.errores.app.domain.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> listar();

    Usuario obtenerPorId(Integer id) throws UsuarioNoEncontradoException;
}
