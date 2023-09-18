package es.unican.CIBEL.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unican.CIBEL.domain.Control;
import es.unican.CIBEL.domain.ControlApp;
import es.unican.CIBEL.domain.ControlDevice;
import es.unican.CIBEL.repository.ControlAppRepository;
import es.unican.CIBEL.repository.ControlDeviceRepository;
import es.unican.CIBEL.repository.ControlRepository;

@Service
public class ControlService {
	
	@Autowired
	private ControlRepository repository;
	
	@Autowired
	private ControlAppRepository repositoryApps;
	
	@Autowired
	private ControlDeviceRepository repositoryDevices;
	
	public List<Control> controles() {
		return repository.findAll();
	}
	
	public List<ControlApp> controlesDeApps() {
		return repositoryApps.findAll();
	}
	
	public List<ControlDevice> controlesDeDispositivos() {
		return repositoryDevices.findAll();
	}
	
	public Control buscaControl(Long id) {
		return repository.findById(id).orElse(null);
	}
	
}
