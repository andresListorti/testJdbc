package com.testClase06.jdbc.testJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PersonaDAO {
    // Data Access Object 

    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    // Metodos CRUD

    // Read 
    public List<Persona> findAll() {
        List<Persona> listaAux = new ArrayList<>();

        try {
            conn = Conexion.obtenerConexion();
            String sql = "SELECT * FROM Persona";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Persona p = new Persona();
                p.setId(rs.getString("id"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setDni(rs.getString("dni"));
                p.setEdad(rs.getInt("edad"));
                p.setTelefono(rs.getString("telefono"));
                p.setEmail(rs.getString("email"));
                p.setDireccionId(rs.getLong("direccion_id"));
                listaAux.add(p);
                
                
            }

            return listaAux;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

        // crearPersona
    public void crearPersona(Persona p) {
        /*
         * Vamos a crear la sentencia para guardar una persona.
         */
        String sql = String.format(
                "INSERT INTO persona (id, nombre, apellido, telefono, email, dni, edad, direccion_id) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s')",
                UUID.randomUUID().toString(),
                p.getNombre(),
                p.getApellido(),
                p.getTelefono(),
                p.getEmail(),
                p.getDni(),
                p.getEdad(),
                p.getDireccionId());

        try {
            conn = Conexion.obtenerConexion();
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Error al insertar persona a la base de datos: " + e.getMessage());
        }

    }

    public Persona findById(Long id) {
        /*
         * Vamos a buscar una persona por su id.
         */
        Persona p = new Persona();
        try {
            conn = Conexion.obtenerConexion();
            String sql = "SELECT * FROM persona WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    p.setId(rs.getString("id"));
                    p.setNombre(rs.getString("nombre"));
                    p.setApellido(rs.getString("apellido"));
                    p.setDni(rs.getString("dni"));
                    p.setTelefono(rs.getString("telefono"));
                    p.setEmail(rs.getString("email"));
                    p.setEdad(rs.getInt("edad"));
                    p.setDireccionId(rs.getLong("direccion_id"));
                }
                System.out.println("Existe el id");
            } else {
                System.out.println("No existe el id");
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return p;
    }

    public void updatePersona(String dni, int edad) {
        /*
         * Vamos a actualizar una persona.
         */
        conn = Conexion.obtenerConexion();

        try {
            String sql = String.format("update persona SET edad = ? where dni = ?");

            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, 666);
            stmt.setString(2, dni);

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Actualizamos a la persona con dni: " + dni);
            } else {
                System.out.println("No se encontro a la persona con dni: " + dni);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public List<Persona> findPersonasPorEdad(int edad) {
        /*
         * Vamos a buscar una persona por su id.
         */
        List<Persona> personas = new ArrayList<>();
        try {
            conn = Conexion.obtenerConexion();
            String sql = "SELECT * FROM persona WHERE edad >= ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, edad);
            rs = stmt.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    Persona p = new Persona();
                    p.setId(rs.getString("id"));
                    p.setNombre(rs.getString("nombre"));
                    p.setApellido(rs.getString("apellido"));
                    p.setDni(rs.getString("dni"));
                    p.setTelefono(rs.getString("telefono"));
                    p.setEmail(rs.getString("email"));
                    p.setEdad(rs.getInt("edad"));
                    p.setDireccionId(rs.getLong("direccion_id"));
                    personas.add(p);
                }
                System.out.println("Existe el id");
            } else {
                System.out.println("No existe el id");
                return null;
            }
        } catch (

        Exception e) {
            System.out.println(e.getMessage());
        }
        return personas;
    }

    public List<Persona> personasPorInicial(String inicial) {
        List<Persona> personas = new ArrayList<>();
        try {
            conn = Conexion.obtenerConexion();
            String sql = "SELECT * FROM persona WHERE nombre LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, inicial + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Persona p = new Persona();
                p.setId(rs.getString("id"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setDni(rs.getString("dni"));
                p.setTelefono(rs.getString("telefono"));
                p.setEmail(rs.getString("email"));
                p.setEdad(rs.getInt("edad"));
                p.setDireccionId(rs.getLong("direccion_id"));
                personas.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return personas;
    }

    // borrarPersonaPersona
}
