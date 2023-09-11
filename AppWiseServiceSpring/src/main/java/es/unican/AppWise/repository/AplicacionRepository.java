package es.unican.AppWise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.AppWise.domain.Aplicacion;
import es.unican.AppWise.domain.Categoria;

public interface AplicacionRepository extends JpaRepository<Aplicacion, Long> {
	
	public List<Aplicacion> findByCategoria(Categoria categoria);

	public Aplicacion findByNombre(String nombre);
}
