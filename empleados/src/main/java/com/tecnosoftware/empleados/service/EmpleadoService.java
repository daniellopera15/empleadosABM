package com.tecnosoftware.empleados.service;

import com.tecnosoftware.empleados.model.Empleado;
import com.tecnosoftware.empleados.repository.EmpleadoInterfaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpleadoService {
    // Lista de empleados

    // CRUD/ABM de empleados

    @Autowired
    private EmpleadoInterfaceRepository empleadoInterfaceRepository;

    public void crearEmpleado(Empleado empleado) {
        empleadoInterfaceRepository.save(empleado);
    }

    public boolean editarTodo(Integer empleadoId, Empleado empleadomodificado) {

        if (empleadoInterfaceRepository.findOne(empleadoId) == null) {
            return false;
        }

        empleadomodificado.setId(empleadoId);
        empleadoInterfaceRepository.save(empleadomodificado);
        return true;
    }

    public void eliminarEmpleado(Integer empleadoId) {
        empleadoInterfaceRepository.delete(empleadoId);
    }


    public List<Empleado> list() {
        return empleadoInterfaceRepository.findAll();
    }

    public Empleado buscarEmpleado(Integer empleadoId) {
        return empleadoInterfaceRepository.findOne(empleadoId);
    }

    public List<Empleado> buscarPorEdad(int edad) {
        return empleadoInterfaceRepository.findByEdad(edad);
    }
}
