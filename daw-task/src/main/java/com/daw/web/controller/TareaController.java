package com.daw.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.Tarea;
import com.daw.service.TareaService;

@RestController
@RequestMapping("/tareas")
public class TareaController {

	@Autowired
	private TareaService tareaService;
	
	public List<Tarea> list(){
		return tareaService.findAll();
	}
	
	public Tarea findById(int idTarea) {
		return this.tareaService.findById(idTarea);
	}
}
