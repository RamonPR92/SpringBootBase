package com.ramon.errores.app.service;

import com.ramon.errores.app.domain.Usuario;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private List<Usuario> usuarios;

    public UsuarioServiceImpl() {
        usuarios = Arrays.asList(
                new Usuario(1, "Ramon", "Perez"),
                new Usuario(2, "Juan", "Lopez"),
                new Usuario(3, "Leo", "Morales"),
                new Usuario(4, "Pancho", "Gutierrez"),
                new Usuario(5, "Enrique", "Ruiz")
        );
    }

    @Override
    public List<Usuario> listar() {
        return usuarios;
    }

    @Override
    public Usuario obtenerPorId(Integer id) throws UsuarioNoEncontradoException {
        return usuarios.stream()
                .filter(usuario -> usuario.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado con el id: " + id));
    }
}
