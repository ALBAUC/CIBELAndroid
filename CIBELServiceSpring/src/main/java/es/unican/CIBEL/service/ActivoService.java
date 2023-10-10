package es.unican.CIBEL.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unican.CIBEL.domain.Categoria;
import es.unican.CIBEL.domain.Activo;
import es.unican.CIBEL.repository.ActivoRepository;

@Service
public class ActivoService {
	
	@Autowired
	private ActivoRepository repository;
	
	public List<Activo> activos() {
		return repository.findAll();
	}
	
	public List<Activo> activosPorCategoria(Categoria categoria) {
		return repository.findByCategoria(categoria);
	}
	
	public Activo buscaActivo(String nombre) {
		return repository.findByNombre(nombre);
	}
}