package com.ramon.forms.app.services;

import com.ramon.forms.app.model.Pais;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PaisServiceImpl implements PaisService{
    private List<Pais> paises;

    public PaisServiceImpl() {
        paises = Arrays.asList(
                new Pais(1, "MX","Mexico"),
                new Pais(2, "EUA", "Estado Unidos"),
                new Pais(3, "COL","Colombia"),
                new Pais(4, "CL","Chile"),
                new Pais(5, "BR","Brasil"),
                new Pais(6, "AR","Argentina"));
    }

    @Override
    public List<Pais> listar() {
        return paises;
    }

    @Override
    public Pais obtenerPorId(Integer id) {
        return paises.stream()
                .filter(pais -> pais.getId().equals(id))
                .findFirst()
                .get();
    }
}
