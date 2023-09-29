package es.unican.CIBEL.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.CIBEL.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	Categoria findByNombre(String nombre);
	
	List<Categoria> findAllByTipo(String tipo);

}
