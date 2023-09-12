package es.unican.CIBEL.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.CIBEL.domain.Categoria;
import es.unican.CIBEL.domain.DispositivoIot;

public interface DispositivoIoTRepository extends JpaRepository<DispositivoIot, Long> {
	
	public List<DispositivoIot> findByCategoria(Categoria categoria);
	
	public DispositivoIot findByNombre(String nombre);
}
