package com.daw.persistence.entity;

import com.daw.persistence.entity.enums.Pokeball;
import com.daw.persistence.entity.enums.Tipo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pokemon")
@Getter
@Setter
@NoArgsConstructor
public class Pokemon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int idPokemon;
	
	@Column(name = "numero_pokedex")
	private int numPokedex;
	
	private String nombre;
	
	private Tipo tipo1;
	
	private Tipo tipo2;
	
	@Column(name = "capturado")
	private Pokeball pokeball;
}
