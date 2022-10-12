package com.ramon.forms.app.editors;

import com.ramon.forms.app.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class RolePropertyEditor extends PropertyEditorSupport {

    @Autowired
    private RoleService roleService;

    @Override
    public void setAsText(String idString) throws IllegalArgumentException {
        Integer id = Integer.parseInt(idString);
        setValue(roleService.obtenerRolePorId(id));
    }
}
