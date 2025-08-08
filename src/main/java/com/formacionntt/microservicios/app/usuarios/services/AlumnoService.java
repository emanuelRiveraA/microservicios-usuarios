package com.formacionntt.microservicios.app.usuarios.services;

import java.util.Optional;

import com.formacionntt.microservicios.app.usuarios.models.entity.Alumno;

//Define una interfaz llamada AlumnoService.
public interface AlumnoService {

 // Declara un método para obtener todos los alumnos.
 // Devuelve un Iterable, lo que permite recorrer la colección con un for-each.
 public Iterable<Alumno> findAll();

 // Declara un método para buscar un alumno por su ID.
 // Devuelve un Optional<Alumno>, que puede contener un alumno o estar vacío si no se encuentra.
 public Optional<Alumno> findById(Long id);

 // Declara un método para guardar un alumno (crear o actualizar).
 // Recibe un objeto Alumno y devuelve el mismo objeto ya persistido.
 public Alumno save(Alumno alumno);

 // Declara un método para eliminar un alumno por su ID.
 // No devuelve nada (void).
 public void deleteById(Long id);
}
