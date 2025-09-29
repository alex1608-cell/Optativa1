package com.daw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Tarea;
import com.daw.persistence.repositories.TareaRepository;

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
		return this.tareaRepository.findById(idTarea).get();
	}
	
	// create 
	
	
	// update
	
	// delete
	
}
