package com.daw.persistence.entities;

import java.time.LocalDateTime;

import com.daw.persistence.entities.enumerados.Metodo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@NoArgsConstructor
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private int id;
	
	private LocalDateTime fecha;
	
	@Column(name = "DECIMAL(6, 2)")
	private double total;
	
	@Column(length = 200)
	private String notas;
	
	@Enumerated(EnumType.STRING)
	private Metodo metodo;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente", referencedColumnName = "id", insertable = false, updatable = false)
	private Cliente cliente;
}
