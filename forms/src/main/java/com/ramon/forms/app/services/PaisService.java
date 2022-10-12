package com.ramon.forms.app.services;

import com.ramon.forms.app.model.Pais;

import java.util.List;

public interface PaisService {

    public List<Pais> listar();
    public Pais obtenerPorId(Integer id);
}
