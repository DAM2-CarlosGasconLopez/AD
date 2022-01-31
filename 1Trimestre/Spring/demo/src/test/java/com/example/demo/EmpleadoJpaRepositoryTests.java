package com.example.demo;

import com.example.repos.iEmpleadosRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase (replace = Replace.NONE)
public class EmpleadoJpaRepositoryTests {

    @Autowired
    private iEmpleadosRepository repo;

    @Test
    public void saveEmpleados() {
        empleado juan = new empleado("Juan", "Saavedra");
        empleado marta = new empleado("Marta", "Sanchez");

        repo.save(juan);
        repo.save(marta);

        repo.flush();

        //assertEquals(2,repo.findAll().size());
        
    }
}
