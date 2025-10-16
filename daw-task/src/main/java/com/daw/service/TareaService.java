package com.daw.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Tarea;
import com.daw.persistence.entities.estados.Estado;
import com.daw.persistence.repositories.TareaRepository;
import com.daw.service.exceptions.TareaException;
import com.daw.service.exceptions.TareaNotFoundException;

@Service
public class TareaService {
	
	@Autowired
	private TareaRepository tareaRepository;

	// findAll
	
	public List<Tarea> findAll(){
		return this.tareaRepository.findAll();
	}
	
	// findByID
	
	public Tarea findById(int idTarea){
	
		if(!this.tareaRepository.existsById(idTarea)) {
			throw new TareaNotFoundException("El id no existe");
		}
		
		
		return this.tareaRepository.findById(idTarea).get();
	}
	
	
	// create 
	
	public Tarea create(Tarea tarea) {
		if(tarea.getFechaVencimiento().isBefore(LocalDate.now())) {
			throw new TareaException("La fecha de vencimiento debe ser posterior.");
		}
		tarea.setId(0);
		tarea.setEstado(Estado.PENDIENTE);
		tarea.setFechaCreacion(LocalDate.now());
		
		return this.tareaRepository.save(tarea);
	}
	
	
	// update
	
	public Tarea update(Tarea tarea, int idTarea) {
		if(tarea.getId() != idTarea) {
			throw new TareaException("El id del body y el del path no coincide");
		}
		if(!this.tareaRepository.existsById(idTarea)) {
			throw new TareaNotFoundException("La tarea con id " + idTarea + " no existe");
		}
		if(tarea.getEstado() != null) {
			throw new TareaException("No se puede modificar el estado");
		}
		if(tarea.getFechaCreacion() != null) {
			throw new TareaException("No se puede modificar la fecha de creacion");
		}
		
		// Recupero la tarea que esta en BBDD y modifico solo los campos permitidos
		// Si guardo directamente la tarea, voy a poner a null fecha de creacion y el estado
		
		Tarea tareaBD = this.findById(idTarea);
		tareaBD.setDescripcion(tarea.getDescripcion());
		tareaBD.setTitulo(tarea.getTitulo());
		tareaBD.setFechaVencimiento(tarea.getFechaVencimiento());
		
		return this.tareaRepository.save(tareaBD);
	}
	
	// delete
		public void delete(int idTarea) {
			if (!this.tareaRepository.existsById(idTarea)) {
				throw new TareaNotFoundException("La tarea no existe");
			}
			this.tareaRepository.deleteById(idTarea);
		}

		public Tarea marcarEnProgreso(int idTarea) {
			Tarea tarea = this.findById(idTarea);

			if (!tarea.getEstado().equals(Estado.PENDIENTE)) {
				throw new TareaException("La tarea ya está completada o ya está en progreso");
			}

			tarea.setEstado(Estado.EN_PROGRESO);
			return this.tareaRepository.save(tarea);
		}
		
		// Completar una tarea
		
		public Tarea completarTarea(int idTarea) {
		
			Tarea tarea = findById(idTarea);
			
			if(tarea.getEstado() != Estado.EN_PROGRESO) {
				throw new TareaNotFoundException("La tarea solo se puede completar cuando esta EN PROGRESO");
			}
			tarea.setEstado(Estado.COMPLETADO);
			return tareaRepository.save(tarea);
		}
		
	// Tarea pendientes
		
		public List<Tarea> pendientes(){
			return this.tareaRepository.findByEstado(Estado.PENDIENTE);
		}
	
	// Tarea completada
		
		public List<Tarea> completadas(){
			return this.tareaRepository.findByEstado(Estado.COMPLETADO);
		}
		
	// Tarea en progreso
		
		public List<Tarea> enProgreso(){
			return this.tareaRepository.findByEstado(Estado.EN_PROGRESO);
		}
		
	// Obtener las tareas vencidas.
		
		public List<Tarea> tareasVencidas(){
			return this.tareaRepository.findAll().stream()
					.filter(t -> t.getFechaVencimiento().isBefore(LocalDate.now()))
					.collect(Collectors.toList());
		}
		
	// Obtener las tareas NO VENCIDAS.
		
		public List<Tarea> tareasNoVencidas(){
			return this.tareaRepository.findAll().stream()
					.filter(t -> t.getFechaVencimiento().isAfter(LocalDate.now()))
					.collect(Collectors.toList());
		}
		
	// Obtener tareas mediante su titulo
		
		public List<Tarea> tareaBuscarTitulo(String tituloBuscado){
			return this.tareaRepository.findAll().stream()
					.filter(t -> t.getTitulo().equalsIgnoreCase(tituloBuscado))
					.collect(Collectors.toList());
		
		}
}
