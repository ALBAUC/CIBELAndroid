package es.unican.CIBEL.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unican.CIBEL.domain.Aplicacion;
import es.unican.CIBEL.domain.Categoria;
import es.unican.CIBEL.domain.DispositivoIot;
import es.unican.CIBEL.domain.ElementoDigital;
import es.unican.CIBEL.repository.AplicacionRepository;
import es.unican.CIBEL.repository.DispositivoIoTRepository;
import es.unican.CIBEL.repository.ElementoDigitalRepository;

@Service
public class ElementoDigitalService {
	
	@Autowired
	private ElementoDigitalRepository repository;
	
	@Autowired
	private DispositivoIoTRepository repositoryDevices;
	
	@Autowired
	private AplicacionRepository repositoryApps;
	
	public List<ElementoDigital> elementosDigitales() {
		return repository.findAll();
	}
	
	public List<ElementoDigital> elementosDigitalesPorCategoria(Categoria categoria) {
		return repository.findByCategoria(categoria);
	}
	
	public ElementoDigital buscaElementoDigital(String nombre) {
		return repository.findByNombre(nombre);
	}
	
	public List<DispositivoIot> dispositivos() {
		return repositoryDevices.findAll();
	}
	
	public List<DispositivoIot> dispositivosPorCategoria(Categoria categoria)  {
		return repositoryDevices.findByCategoria(categoria);
	}
	
	public DispositivoIot buscaDispositivo(String nombre) {
		return repositoryDevices.findByNombre(nombre);
	}
	
	public List<Aplicacion> aplicaciones() {
		return repositoryApps.findAll();
	}
	
	public List<Aplicacion> aplicacionesPorCategoria(Categoria categoria) {
		return repositoryApps.findByCategoria(categoria);
	}
	
	public Aplicacion buscaAplicacion(String nombre) {
		return repositoryApps.findByNombre(nombre);
	}
}
