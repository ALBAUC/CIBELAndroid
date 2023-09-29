package es.unican.CIBEL.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.CIBEL.domain.Control;

public interface ControlRepository extends JpaRepository<Control, Long> {
	
	List<Control> findAllByTipo(String tipo);
}
