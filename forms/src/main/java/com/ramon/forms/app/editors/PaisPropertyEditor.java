package com.ramon.forms.app.editors;

import com.ramon.forms.app.services.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class PaisPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private PaisService paisService;

    @Override
    public void setAsText(String idString) throws IllegalArgumentException {
        Integer id = Integer.parseInt(idString);
        setValue(paisService.obtenerPorId(id));
    }
}
