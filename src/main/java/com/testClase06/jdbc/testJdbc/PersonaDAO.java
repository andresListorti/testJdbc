package com.testClase06.jdbc.testJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

}
