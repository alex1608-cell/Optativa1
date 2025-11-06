package pruebaPracticaUD1.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pruebaPracticaUD1.persistence.entities.Registro;
import pruebaPracticaUD1.persistence.repositories.RegistroRepository;
import pruebaPracticaUD1.services.exceptions.RegistroException;
import pruebaPracticaUD1.services.exceptions.RegistroNotFoundException;

@Service
public class RegistroService {

	
	@Autowired
	private RegistroRepository registroRepository;
	
	// findAll
	
	public List<Registro> findAll(){
		return this.registroRepository.findAll();
	}
	
	// findById
		public Registro findById(int idRegistro) {
			if (!this.registroRepository.existsById(idRegistro)) {
				throw new RegistroNotFoundException("La tarea con id " + idRegistro + " no existe. ");
			}

			return this.registroRepository.findById(idRegistro).get();
		}
		
	
	// update mediante su ID
	
	public Registro update(Registro registro, int idRegistro) {
		if(registro.getIdRegistro() != idRegistro) {
			throw new RegistroException(String.format("El ID del body (%d) y el del path (%d) no coinciden", registro.getIdRegistro(), idRegistro));
		}
		
		if (!this.registroRepository.existsById(idRegistro)) {
			throw new RegistroNotFoundException("El registro con id " + idRegistro + " no existe. ");
		}
		
		if (registro.getFechaLectura() != null) {
			throw new RegistroException("No se puede modificar la fecha de lectura. ");
		}
		
		if (registro.getPrecipitacion() != null) {
			throw new RegistroException("No se puede modificar la precipitacion. ");
		}
		
		Registro registroDB = this.findById(idRegistro);
		registroDB.setUbicacion(registro.getUbicacion());
		registroDB.setTemperatura(registro.getTemperatura());
		registroDB.setUnidad(registro.getUnidad());
		
		return this.registroRepository.save(registroDB);
	}
	
	
	// Modificar precipitacion
	
	public Registro updatePrecipitacion( int idRegistro, double anterior, double nueva) {
		if(!this.registroRepository.existsById(idRegistro)) {
			throw new RegistroException("El registro con Id " + idRegistro + "no existe.");
		}
		
		Registro registroDB = this.findById(idRegistro);
		
		if(anterior == nueva) {
			throw new RegistroException("La precipitacion anterior y la nueva no pueden ser iguales");
		}
		if(registroDB.getPrecipitacion() != anterior) {
			throw new RegistroException("La precipitacion anterior no coincide con la base de datos");
		}
		registroDB.setPrecipitacion(nueva);
		
		return this.registroRepository.save(registroDB);
	}
	
	// Buscar los registros por ubicacion y rango fechas
	
	public List<Registro> buscarPorUbiYFechas(String ubicacion, LocalDate inicio, LocalDate fin ){
		return this.registroRepository.findByUbicacionAndFechaLecturaBetween(ubicacion, inicio, fin);
	}
	

}
