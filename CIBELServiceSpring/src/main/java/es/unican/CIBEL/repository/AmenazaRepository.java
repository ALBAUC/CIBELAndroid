package es.unican.CIBEL.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.CIBEL.domain.Amenaza;

public interface AmenazaRepository extends JpaRepository<Amenaza, Long> {
	
}
