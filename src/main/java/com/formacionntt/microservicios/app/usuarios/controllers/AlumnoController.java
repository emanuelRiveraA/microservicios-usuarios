package com.formacionntt.microservicios.app.usuarios.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	// Anotación que indica que este método manejará solicitudes HTTP POST.
	// Se usa para crear nuevos recursos (en este caso, un alumno).
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Alumno alumno){
		 // Llama al servicio para guardar el alumno en la base de datos.
	    // El método save puede crear o actualizar, pero aquí se usa para crear.
		Alumno alumnoDB = alumnoService.save(alumno);
		 // Devuelve una respuesta HTTP con estado 201 (CREATED) y el alumno como cuerpo.
		return ResponseEntity.status(HttpStatus.CREATED).body(alumno);
	}
	
	// Anotación que indica que este método manejará solicitudes HTTP PUT.
	// Se usa para actualizar un recurso existente, identificado por su ID.
	@PutMapping("/{id}")             //Captura el cuerpo de la solicitud (en formato JSON) y lo convierte en un objeto Alumno
	public ResponseEntity<?> editar(@RequestBody Alumno alumno, @PathVariable Long id){ //Captura el valor {id} de la URL y lo asigna a la variable id
		//1.- se buesca en la BD por el ID
		Optional<Alumno> alumnoOptional = alumnoService.findById(id);
		//2.- se valida que exista el registro
		if (alumnoOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		//3.- si existe lo obtenemos, y lo guardamos en una nueva variable
		//y cambiamos los datos por los que vienen en el request. NOTA: la fecha no la cambiamos porque es la creacion
		Alumno alumnoDB = alumnoOptional.get();
		alumnoDB.setNombre(alumno.getNombre());
		alumnoDB.setApellido(alumno.getApellido());
		alumnoDB.setEmail(alumno.getEmail());
		
		//4.- lo persistimos con el metodo save. Nos retornara el objeto actualizado y automaticamente 
		//se pasa al cuerpo de la respuesta
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoService.save(alumnoDB));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		 // Llama al servicio para eliminar el alumno por su ID.
		alumnoService.deleteById(id);
		// Devuelve una respuesta HTTP 204 (No Content), indicando que la operación fue exitosa pero no hay contenido que devolver.
		return ResponseEntity.noContent().build();//.build(): Finaliza la construcción de la respuesta sin cuerpo.
	}
	
}
