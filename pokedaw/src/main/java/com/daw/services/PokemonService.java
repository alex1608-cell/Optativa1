package com.daw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entity.Pokemon;
import com.daw.persistence.repositories.PokemonRepository;
import com.daw.services.exceptions.PokemonNotFoundException;

@Service
public class PokemonService {

	 @Autowired
	 private PokemonRepository pokemonRepository;
	 
	 // En la capa de servicio va todas las cosas que hay que hacer
	 //Aqui no se ponen nada de @GetMapping ni nada de eso
	 
	 // Obtener todos los pokemon
	 
	 List<Pokemon> findAll(){
		 return this.pokemonRepository.findAll();
	 }
	 
	 //Obtener un pokemon por ID
	 
	public Pokemon findById(int idPokemon){
		if(!this.pokemonRepository.existsById(idPokemon)) {
			throw new PokemonNotFoundException("El Id no existe");
		}
		return this.pokemonRepository.findById(idPokemon).get();
	 }
	 
}
