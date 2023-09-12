package es.unican.CIBEL.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.CIBEL.domain.Categoria;
import es.unican.CIBEL.domain.ElementoDigital;

public interface ElementoDigitalRepository extends JpaRepository<ElementoDigital, Long> {
	
	public List<ElementoDigital> findByCategoria(Categoria categoria);
	
	public ElementoDigital findByNombre(String nombre);
}
