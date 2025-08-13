package com.formacionntt.microservicios.app.usuarios.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.formacionntt.microservicios.app.usuarios.models.entity.Alumno;
import com.formacionntt.microservicios.app.usuarios.services.AlumnoService;
import com.formacionntt.microservicios.commons.controllers.CommonController;

@RestController
public class AlumnoController extends CommonController<Alumno, AlumnoService>{
	
	// Anotación que indica que este método manejará solicitudes HTTP PUT.
	// Se usa para actualizar un recurso existente, identificado por su ID.
	@PutMapping("/{id}")             //Captura el cuerpo de la solicitud (en formato JSON) y lo convierte en un objeto Alumno
	public ResponseEntity<?> editar(@RequestBody Alumno alumno, @PathVariable Long id){ //Captura el valor {id} de la URL y lo asigna a la variable id
		//1.- se buesca en la BD por el ID
		Optional<Alumno> alumnoOptional = service.findById(id);
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
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDB));
	}
	
	
}
