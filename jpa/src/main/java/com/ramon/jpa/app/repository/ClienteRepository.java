package com.ramon.jpa.app.repository;

import com.ramon.jpa.app.domain.Cliente;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//Ejemplo con entityManager
public class ClienteRepository {
//    public class ClienteRepository implements IClienteRepository{

//    //H2 es la baase de datos utilizada por defecto cuando no se indica otra en le config
//
//    //Hace la inyeccion del entity manager, con esto nos comunicaremos con la BD
//    @PersistenceContext
//    private EntityManager entityManager;
//
//
//    @Override
//    public List<Cliente> findAll() {
//        return entityManager.createQuery("FROM Cliente").getResultList();
//    }
//
//
//    @Override
//    public void save(Cliente cliente) {
//        //Si no hay id entonces es un nuevo cliente
//        if (cliente.getId() == null) {
//            entityManager.persist(cliente);
//        }else{
//            //Si ya habia un id, entonces se actualiza el cliente
//            entityManager.merge(cliente);
//        }
//    }
//
//
//    @Override
//    public Cliente findById(Long id) {
//        return entityManager.find(Cliente.class, id);
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        Cliente client = findById(id);
//        entityManager.remove(client);
//    }
}
