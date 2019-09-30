package com.asymmetric.demo2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asymmetric.demo2.model.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

	
	
}
