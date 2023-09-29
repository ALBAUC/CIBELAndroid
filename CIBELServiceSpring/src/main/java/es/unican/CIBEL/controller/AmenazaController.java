package es.unican.CIBEL.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.unican.CIBEL.domain.Amenaza;
import es.unican.CIBEL.service.AmenazaService;

@RestController
@RequestMapping("/amenazas")
public class AmenazaController {
	
	@Autowired
	private AmenazaService amenazaService;
	
	@GetMapping
	public List<Amenaza> getAmenazas() {
		return amenazaService.amenazas();
	}
	
	@GetMapping("/apps")
	public List<Amenaza> getAmenazasDeApps() {
		return amenazaService.amenazasDeApps();
	}
	
	@GetMapping("/dispositivos")
	public List<Amenaza> getAmenazasDeDispositivos() {
		return amenazaService.amenazasDeDispositivos();
	}
}
