package com.testClase06.jdbc.testJdbc;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Persona {

    private String id;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String email;
    private int edad;
    private Long direccionId;

    public Persona(String nombre, String apellido, String dni, int edad, String telefono, String email,
            Long direccionId) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.telefono = telefono;
        this.email = email;
        this.direccionId = direccionId;
    }

}
