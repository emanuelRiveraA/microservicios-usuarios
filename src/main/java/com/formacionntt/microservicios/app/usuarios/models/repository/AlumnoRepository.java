package com.formacionntt.microservicios.app.usuarios.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.formacionntt.microservicios.app.usuarios.models.entity.Alumno;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {

}
