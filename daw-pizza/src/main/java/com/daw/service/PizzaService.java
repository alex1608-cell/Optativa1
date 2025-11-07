package com.daw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Pizza;
import com.daw.persistence.repositories.PizzaRepository;
import com.daw.service.exception.PizzaExceptions;
import com.daw.service.exception.PizzaNotFoundException;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepository pizzaRepository;

	// Obtener todas las pizzas.
	public List<Pizza> findAll() {
		return this.pizzaRepository.findAll();
	}

	// Obtener una pizza mediante su ID
	public Pizza findById(int idPizza) {
		if (!this.pizzaRepository.existsById(idPizza)) {
			throw new PizzaNotFoundException("El ID: " + idPizza + " no se encuentra.");
		}
		return this.pizzaRepository.findById(idPizza).get();
	}

	// Crear una pizza.
	public Pizza createPizza(Pizza pizza) {
		if (pizza.getNombre() == null || pizza.getNombre().isEmpty()) {
			throw new PizzaExceptions("La pizza no puede ir sin nombre. ");
		}
		if (pizza.getDescripcion() == null || pizza.getDescripcion().isEmpty()) {
			throw new PizzaExceptions("La pizza debe contener una descipcion.");
		}
		if (pizza.getPrecio() == null || pizza.getPrecio() <= 0) {
			throw new PizzaExceptions("La pizza debe contener un precio");
		}
		return this.pizzaRepository.save(pizza);
	}

	// Modificar una pizza
	public Pizza updatePizza(Pizza pizza, int idPizza) {
		if (pizza.getId() != idPizza) {
			throw new PizzaExceptions("El id del body y el id del path no coinciden");
		}
		if(!this.pizzaRepository.existsById(idPizza)) {
			throw new PizzaNotFoundException("El ID: " + idPizza + " no se encuentra.");
		}
		if (pizza.getPrecio() == null || pizza.getPrecio() <= 0) {
			throw new PizzaExceptions("La pizza debe contener un precio");
		}
		if(pizza.getDisponible() == null) {
			throw new PizzaExceptions("Debes confirmar si la pizza esta disponible");
		}
		if(pizza.getVegana() == null) {
			throw new PizzaExceptions("Debes confirmar si la pizza es vegana");
		}
		if(pizza.getVegetariana() == null) {
			throw new PizzaExceptions("Debes confirmas si la pizza es vegetariana");
		}
		Pizza pizzaDB = this.findById(idPizza);
		pizzaDB.setPrecio(pizza.getPrecio());
	    pizzaDB.setDisponible(pizza.getDisponible());
	    pizzaDB.setVegana(pizza.getVegana());
	    pizzaDB.setVegetariana(pizza.getVegetariana());
		
	    return this.pizzaRepository.save(pizzaDB);
	}
	
	public void deletePizza(Pizza pizza, int idPizza) {
		if(!this.pizzaRepository.existsById(idPizza)) {
			throw new PizzaNotFoundException("La pizza elegida no existe");
		}
		this.pizzaRepository.deleteById(idPizza);
	}
	
}
