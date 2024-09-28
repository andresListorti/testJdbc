package com.testClase06.jdbc.testJdbc;

import java.sql.Connection;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestJdbcApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(TestJdbcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Hola Spring");
		
		PersonaDAO dao = new PersonaDAO();
		System.out.println("*****************Todas*****************************");
		dao.findAll().stream().forEach(System.out::println);

		//dao.findPersonasPorEdad(29).stream().forEach(x -> x.toString());
		System.out.println("**********************por edad mas 29************************");
		for (Persona persona : dao.findPersonasPorEdad(29)) {
		System.out.println(persona);
		}

		System.out.println("****************por inicial M******************************");
		//dao.personasPorInicial("M").stream().forEach(x -> x.toString());
		for (Persona persona : dao.personasPorInicial("M")) {
			System.out.println(persona);
		}

	}

}
