package es.unican.CIBEL.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unican.CIBEL.domain.Control;
import es.unican.CIBEL.repository.ControlRepository;

@Service
public class ControlService {
	
	@Autowired
	private ControlRepository repository;
	
	public List<Control> controles() {
		return repository.findAll();
	}
	
	public Control buscaControl(Long id) {
		return repository.findById(id).orElse(null);
	}
	
}
