package pruebaPracticaUD1.web.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pruebaPracticaUD1.persistence.entities.Registro;
import pruebaPracticaUD1.services.RegistroService;
import pruebaPracticaUD1.services.exceptions.RegistroException;
import pruebaPracticaUD1.services.exceptions.RegistroNotFoundException;

@RestController
@RequestMapping("/registro")
public class RegistroController {

	@Autowired
	private RegistroService registroService;

	@GetMapping
	public ResponseEntity<List<Registro>> list() {
		return ResponseEntity.ok(this.registroService.findAll());
	}

	@PutMapping("{idRegistro}")
	public ResponseEntity<?> update(@PathVariable int idRegistro, @RequestBody Registro registro) {
		try {
			return ResponseEntity.ok(this.registroService.update(registro, idRegistro));
		} catch (RegistroNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		} catch (RegistroException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}

	// Modificar precipitacion

	@PutMapping("{idRegistro}/precipitacion")
	public ResponseEntity<?> updatePrecipitacion(@PathVariable int idRegistro, @RequestParam double anterior,
			@RequestParam double nueva) {
		try {
			return ResponseEntity.ok(this.registroService.updatePrecipitacion(idRegistro, anterior, nueva));
		} catch (RegistroNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		} catch (RegistroException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	// Buscar por ubicacion y fechas

		@GetMapping("/buscar")
		public ResponseEntity<?> buscarPorUbiYFechas(@RequestParam String ubicacion,@RequestParam LocalDate inicio, @RequestParam LocalDate fin ){
			List<Registro> resultados = this.registroService.buscarPorUbiYFechas(ubicacion, inicio, fin);
		    return ResponseEntity.ok(resultados); 
		}

	
}
