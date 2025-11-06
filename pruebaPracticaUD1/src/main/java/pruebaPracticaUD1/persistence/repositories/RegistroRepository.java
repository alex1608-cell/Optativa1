package pruebaPracticaUD1.persistence.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import pruebaPracticaUD1.persistence.entities.Registro;

public interface RegistroRepository  extends ListCrudRepository<Registro, Integer>{

    List<Registro> findByUbicacionAndFechaLecturaBetween(String ubicacion, LocalDate inicio, LocalDate fin);

}
