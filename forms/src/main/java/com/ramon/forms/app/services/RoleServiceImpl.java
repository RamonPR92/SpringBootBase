package com.ramon.forms.app.services;

import com.ramon.forms.app.model.Role;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private List<Role> roles;

    public RoleServiceImpl() {
        roles = Arrays.asList(
                new Role(1, "Administrador", "ROLE_ADMIN"),
                new Role(2, "Usuario", "ROLE_USER"),
                new Role(3, "Moderador", "ROLE_MODERATOR")
        );
    }

    @Override
    public List<Role> listar() {
        return roles;
    }

    @Override
    public Role obtenerRolePorId(Integer id) {
        return roles.stream()
                .filter(role -> role.getId().equals(id))
                .findFirst().get();
    }
}
