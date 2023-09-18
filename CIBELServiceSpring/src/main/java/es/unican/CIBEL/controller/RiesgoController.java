package es.unican.CIBEL.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.unican.CIBEL.domain.Riesgo;
import es.unican.CIBEL.domain.RiesgoApp;
import es.unican.CIBEL.domain.RiesgoDevice;
import es.unican.CIBEL.service.RiesgoService;

@RestController
@RequestMapping("/riesgos")
public class RiesgoController {
	
	@Autowired
	private RiesgoService riesgoService;
	
	@GetMapping
	public List<Riesgo> getRiesgos() {
		return riesgoService.riesgos();
	}
	
	@GetMapping("/apps")
	public List<RiesgoApp> getRiesgosDeApps() {
		return riesgoService.riesgosDeApps();
	}
	
	@GetMapping("/dispositivos")
	public List<RiesgoDevice> getRiesgosDeDispositivos() {
		return riesgoService.riesgosDeDispositivos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Riesgo> getRiesgo(@PathVariable Long id) {
		ResponseEntity<Riesgo> result;
		Riesgo riesgo = riesgoService.buscaRiesgo(id);
		
		if (riesgo == null) {
			result = ResponseEntity.notFound().build();
		} else {
			result = ResponseEntity.ok(riesgo);
		}
		
		return result;
	}
}
