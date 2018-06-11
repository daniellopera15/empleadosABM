package com.tecnosoftware.empleados.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.*;



@Entity
@Table(name = "Empleado")
public class Empleado {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@NotNull
    @NotBlank
    @NotEmpty
    @Column(name="nombre")
    private String nombre;
    @NotNull
    @Min(value = 18, message = "Debe ser mayor de edad")
    @Max(value = 65, message = "Estas muy viejo")
    @Column(name="edad")
    private int edad;
    @NotNull
    @DecimalMin("9500")
    @DecimalMax("1000000.00")
    @Column(name="sueldo")
    private Double sueldo;

    public Empleado() {}

    public Empleado(int id, String nombre, int edad, Double sueldo) {
        this.nombre = nombre;
        this.edad = edad;
        this.sueldo = sueldo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public void setId(int id) { this.id = id; }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", sueldo=" + sueldo +
                '}';
    }

    public void cambiarDato(Empleado empleado) {
        if (empleado.getNombre() != null) this.setNombre(empleado.getNombre());
        if (empleado.getSueldo() != null || !empleado.getSueldo().equals(0)) this.setSueldo(empleado.getSueldo());
        if (empleado.getEdad() != 0) this.setEdad(empleado.getEdad());
    }
}
