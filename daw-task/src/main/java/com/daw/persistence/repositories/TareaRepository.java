package com.daw.persistence.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.Tarea;
import com.daw.persistence.entities.estados.Estado;

public interface TareaRepository extends ListCrudRepository<Tarea, Integer>{
	
	
	// SELECT * FROM tarea WHERE estado = ?;
	
	List<Tarea> findByEstado(Estado estado);
	
	// Obtener las tareas pendientes.
	
	// SELECT * FROM tarea WHERE estado = 'PENDIENTE';
	
	
	
	// Obtener las tareas en progreso.
	
	// SELECT * FROM tarea WHERE estado = 'EN_PROGRESO';
	
	
	
	// Obtener las tareas completadas.
	
	// SELECT * FROM tarea WHERE estado = 'COMPLETADA';
	
	
	
	// Obtener las tareas vencidas (fecha de vencimiento menor que la de hoy).
	
	// Obtener las tareas no vencidas (fecha de vencimiento mayor que la de hoy).

	// Obtener tareas mediante su título (que contenga el String que se pasa como título).
	
	
	
}
