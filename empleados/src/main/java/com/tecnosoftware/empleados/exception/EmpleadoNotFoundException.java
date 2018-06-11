package com.tecnosoftware.empleados.exception;

public class EmpleadoNotFoundException extends Exception {
    public EmpleadoNotFoundException() {
    }

    public EmpleadoNotFoundException(String message) {
        super(message);
    }
}
