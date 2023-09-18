package es.unican.CIBEL.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unican.CIBEL.domain.Riesgo;
import es.unican.CIBEL.domain.RiesgoApp;
import es.unican.CIBEL.domain.RiesgoDevice;
import es.unican.CIBEL.repository.RiesgoAppRepository;
import es.unican.CIBEL.repository.RiesgoDeviceRepository;
import es.unican.CIBEL.repository.RiesgoRepository;

@Service
public class RiesgoService {
	
	@Autowired
	private RiesgoRepository repository;
	
	@Autowired
	private RiesgoAppRepository repositoryApps;
	
	@Autowired
	private RiesgoDeviceRepository repositoryDevices;
	
	public List<Riesgo> riesgos() {
		return repository.findAll();
	}
	
	public List<RiesgoApp> riesgosDeApps() {
		return repositoryApps.findAll();
	}
	
	public List<RiesgoDevice> riesgosDeDispositivos() {
		return repositoryDevices.findAll();
	}
	
	public Riesgo buscaRiesgo(Long id) {
		return repository.findById(id).orElse(null);
	}
}
