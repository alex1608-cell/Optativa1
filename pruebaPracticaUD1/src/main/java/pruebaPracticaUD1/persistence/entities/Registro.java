package pruebaPracticaUD1.persistence.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pruebaPracticaUD1.persistence.entities.estados.Unidad;

@Entity
@Table(name = "registro")
@Getter
@Setter
@NoArgsConstructor

public class Registro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRegistro;
	
	@Column(name = "fecha_lectura")
	private LocalDate fechaLectura;
	
	private String ubicacion;
	
	private Double temperatura;
	
	@Enumerated(value = EnumType.STRING)
	private Unidad unidad;
	
	private Double precipitacion;
	
	
}
