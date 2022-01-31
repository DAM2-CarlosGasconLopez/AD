package com.example.demo;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CollectionId;

@Entity
public class empleado {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codEmpleado;


    @Column(length = 25, nullable = false)
    private String nombre;

    @Column(length = 25, nullable = true)
    private String apellido;

    


    public empleado(Integer codEmpleado, String nombre, String apellido) {
        this.codEmpleado = codEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public empleado( String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Integer getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(int codEmpleado) {
        this.codEmpleado = codEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "empleado [apellido=" + apellido + ", codEmpleado=" + codEmpleado + ", nombre=" + nombre + "]";
    }
    
    
    
}
