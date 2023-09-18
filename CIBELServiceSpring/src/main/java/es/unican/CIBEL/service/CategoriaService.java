package es.unican.CIBEL.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unican.CIBEL.domain.Categoria;
import es.unican.CIBEL.domain.CategoriaApp;
import es.unican.CIBEL.domain.CategoriaDevice;
import es.unican.CIBEL.repository.CategoriaAppRepository;
import es.unican.CIBEL.repository.CategoriaDeviceRepository;
import es.unican.CIBEL.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	@Autowired
	private CategoriaAppRepository repositoryApps;
	
	@Autowired
	private CategoriaDeviceRepository repositoryDevices;
	
	public List<Categoria> categorias() {
		return repository.findAll();
	}
	
	public List<CategoriaApp> categoriasDeApps() {
		return repositoryApps.findAll();
	}
	
	public List<CategoriaDevice> categoriasDeDispositivos() {
		return repositoryDevices.findAll();
	}
	
	public Categoria buscaCategoria(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public Categoria buscaCategoriaPorNombre(String nombre) {
		return repository.findByNombre(nombre);
	}
}
