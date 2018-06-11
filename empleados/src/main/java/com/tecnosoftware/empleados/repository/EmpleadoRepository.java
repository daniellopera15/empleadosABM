package com.tecnosoftware.empleados.repository;

import com.tecnosoftware.empleados.model.Empleado;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EmpleadoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void create(Empleado empleado) {
        entityManager.persist(empleado);
    }

    public void update(Empleado empleado) {
        entityManager.merge(empleado);
    }

    public Empleado  getApiRequestById(int id) {
        return entityManager.find(Empleado.class, id);
    }
    public List<Empleado> getAll() {
        return entityManager.createQuery("from Empleado").getResultList();
    }

    public void delete(int id) {
        Empleado empleado = getApiRequestById(id);
        if (empleado != null) {
            entityManager.remove(empleado);
        }
    }

}
