package es.unican.CIBEL.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.CIBEL.domain.Amenaza;

public interface AmenazaRepository extends JpaRepository<Amenaza, Long> {
	List<Amenaza> findAllByTipo(String tipo);
}
