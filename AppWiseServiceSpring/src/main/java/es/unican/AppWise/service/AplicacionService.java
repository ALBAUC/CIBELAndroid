package es.unican.AppWise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unican.AppWise.domain.Aplicacion;
import es.unican.AppWise.domain.Categoria;
import es.unican.AppWise.repository.AplicacionRepository;

@Service
public class AplicacionService {
	
	@Autowired
	private AplicacionRepository repository;
	
	public List<Aplicacion> aplicaciones() {
		return repository.findAll();
	}
	
	public List<Aplicacion> aplicacionesPorCategoria(Categoria categoria) {
		return repository.findByCategoria(categoria);
	}
	
	public Aplicacion buscaAplicacion(String nombre) {
		return repository.findByNombre(nombre);
	}
}
