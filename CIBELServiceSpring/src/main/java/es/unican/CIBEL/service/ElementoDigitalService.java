package es.unican.CIBEL.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unican.CIBEL.domain.Categoria;
import es.unican.CIBEL.domain.ElementoDigital;
import es.unican.CIBEL.repository.ElementoDigitalRepository;

@Service
public class ElementoDigitalService {
	
	@Autowired
	private ElementoDigitalRepository repository;
	
	public List<ElementoDigital> elementosDigitales() {
		return repository.findAll();
	}
	
	public List<ElementoDigital> elementosDigitalesPorCategoria(Categoria categoria) {
		return repository.findByCategoria(categoria);
	}
	
	public ElementoDigital buscaElementoDigital(String nombre) {
		return repository.findByNombre(nombre);
	}
}
