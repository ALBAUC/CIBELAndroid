package es.unican.CIBEL.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unican.CIBEL.domain.Riesgo;
import es.unican.CIBEL.repository.RiesgoRepository;

@Service
public class RiesgoService {
	
	@Autowired
	private RiesgoRepository repository;
	
	public List<Riesgo> riesgos() {
		return repository.findAll();
	}
	
	public Riesgo buscaRiesgo(Long id) {
		return repository.findById(id).orElse(null);
	}
}
