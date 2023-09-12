package es.unican.CIBEL.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unican.CIBEL.domain.Categoria;
import es.unican.CIBEL.domain.DispositivoIot;
import es.unican.CIBEL.repository.DispositivoIoTRepository;

@Service
public class DispositivoIoTService {
	
	@Autowired
	private DispositivoIoTRepository repository;
	
	public List<DispositivoIot> dispositivos() {
		return repository.findAll();
	}
	
	public List<DispositivoIot> dispositivosPorCategoria(Categoria categoria)  {
		return repository.findByCategoria(categoria);
	}
	
	public DispositivoIot buscaDispositivo(String nombre) {
		return repository.findByNombre(nombre);
	}
 }
