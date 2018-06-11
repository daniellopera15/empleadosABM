package com.tecnosoftware.empleados.repository;

import com.tecnosoftware.empleados.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoInterfaceRepository extends JpaRepository<Empleado, Integer> {
    public List<Empleado> findByEdad(int edad);
}
