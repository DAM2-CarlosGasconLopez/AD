package com.example.repos;

import com.example.demo.empleado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iEmpleadosRepository extends JpaRepository<empleado,Integer> {

    empleado findBycodEmpleado(Integer codEmpleado);
    empleado findByNombre(String nombre);
    empleado findByApellido(String apellido);
    
}
