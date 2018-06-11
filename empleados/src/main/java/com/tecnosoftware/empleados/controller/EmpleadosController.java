package com.tecnosoftware.empleados.controller;

import com.tecnosoftware.empleados.exception.EmpleadoNotFoundException;
import com.tecnosoftware.empleados.model.Empleado;
import com.tecnosoftware.empleados.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/empleado")
public class EmpleadosController {

    // Injectar service (bean) y utilizarlo para el abm de empleados

    @Autowired
    private EmpleadoService empleadoService;

    @RequestMapping(value = "/crear", method = RequestMethod.POST)
    @CrossOrigin
    public void crearEmpleado(@Valid @RequestBody Empleado empleado) {
        empleadoService.crearEmpleado(empleado);
    }

    @RequestMapping(value = "/editar", method = RequestMethod.PUT)
    @CrossOrigin
    public ResponseEntity<Empleado> editarTodo(@Valid @RequestBody Empleado empleadomodificado) throws EmpleadoNotFoundException {
        if(empleadoService.editarTodo(empleadomodificado.getId(), empleadomodificado)){
            return ResponseEntity.ok(empleadomodificado);
        }

        throw new EmpleadoNotFoundException("No se encontro el empleado con el id: " + empleadomodificado.getId());

    }


    @RequestMapping(value = "/eliminar/{empleadoId}", method = RequestMethod.DELETE)
    @CrossOrigin
    public ResponseEntity<Empleado> eliminarEmpleado(@PathVariable Integer empleadoId) throws EmpleadoNotFoundException {
        Empleado empleado=empleadoService.buscarEmpleado(empleadoId);

        if (empleado == null) {
            throw new EmpleadoNotFoundException("No se encontro el empleado con el id: " + empleadoId);
        }

        empleadoService.eliminarEmpleado(empleadoId);

        return ResponseEntity.ok(empleado);
    }


    @RequestMapping("/list")
    @CrossOrigin
    public List<Empleado> list() {
        return empleadoService.list();
    }

    @RequestMapping("/buscar/edad/{edad}")
    @CrossOrigin
    public List<Empleado> list(@PathVariable int edad) {
        return empleadoService.buscarPorEdad(edad);
    }

    @RequestMapping(value = "/buscar/{empleadoId}")
    @CrossOrigin
    public ResponseEntity<Empleado> buscarEmpleado(@PathVariable Integer empleadoId) throws EmpleadoNotFoundException {
        Empleado empleado = empleadoService.buscarEmpleado(empleadoId);
        if (empleado == null) {
            throw new EmpleadoNotFoundException("No se encontro el empleado con el id: " + empleadoId);
        }
        return ResponseEntity.ok(empleado);
    }


    @ExceptionHandler(EmpleadoNotFoundException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Map<String, String> onException(EmpleadoNotFoundException e) {
        return Collections.singletonMap("mensaje", e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Map<String, Map<String, String>> validationError(MethodArgumentNotValidException ex) {
        Map<String, String> map = new HashMap<>();
        Map<String, Map<String, String>> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        errors.put("errores", map);
        return errors;
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Map<String, Map<String, String>> validationError(BindException ex) {
        Map<String, String> map = new HashMap<>();
        Map<String, Map<String, String>> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        errors.put("errores", map);
        return errors;
    }
}
