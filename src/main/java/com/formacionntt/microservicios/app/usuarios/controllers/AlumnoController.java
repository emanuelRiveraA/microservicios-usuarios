package com.formacionntt.microservicios.app.usuarios.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionntt.microservicios.app.usuarios.models.entity.Alumno;
import com.formacionntt.microservicios.app.usuarios.services.AlumnoService;

@RestController
public class AlumnoController {

	@Autowired
	private AlumnoService alumnoService;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok().body(alumnoService.findAll());
	}
	
	// Anotación que indica que este método responderá a peticiones HTTP GET.
	// El parámetro "{id}" en la URL será capturado como una variable.
	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id){
		 // Llama al servicio para buscar un alumno por su ID.
		Optional<Alumno> optionalAlumno = alumnoService.findById(id);
		// Si el alumno no se encuentra, devuelve una respuesta HTTP 404 (Not Found).
		if (optionalAlumno.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		// Si el alumno existe, devuelve una respuesta HTTP 200 (OK) con el alumno como cuerpo.
		return ResponseEntity.ok(optionalAlumno.get());
	}
}
