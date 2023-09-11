package es.unican.AppWise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.AppWise.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	Categoria findByNombre(String nombre);

}
