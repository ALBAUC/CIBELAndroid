package es.unican.CIBEL.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unican.CIBEL.domain.Amenaza;
import es.unican.CIBEL.repository.AmenazaRepository;

@Service
public class AmenazaService {
	
	@Autowired
	private AmenazaRepository repository;
	
	public List<Amenaza> amenazas() {
		return repository.findAll();
	}
	
	public Amenaza buscaAmenaza(Long id) {
		return repository.findById(id).orElse(null);
	}
}
