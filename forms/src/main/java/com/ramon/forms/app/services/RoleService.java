package com.ramon.forms.app.services;

import com.ramon.forms.app.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> listar();
    Role obtenerRolePorId(Integer id);
}
