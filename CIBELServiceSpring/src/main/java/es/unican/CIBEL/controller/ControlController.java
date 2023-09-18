package es.unican.CIBEL.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.unican.CIBEL.domain.Control;
import es.unican.CIBEL.domain.ControlApp;
import es.unican.CIBEL.domain.ControlDevice;
import es.unican.CIBEL.service.ControlService;

@RestController
@RequestMapping("/controles")
public class ControlController {
	
	@Autowired
	private ControlService controlService;
	
	@GetMapping
	public List<Control> getControles() {
		return controlService.controles();
	}
	
	@GetMapping("/apps")
	public List<ControlApp> getControlesDeApps() {
		return controlService.controlesDeApps();
	}
	
	@GetMapping("/dispositivos")
	public List<ControlDevice> getControlesDeDispositivos() {
		return controlService.controlesDeDispositivos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Control> getControl(@PathVariable Long id) {
		ResponseEntity<Control> result;
		Control control = controlService.buscaControl(id);
		
		if (control == null) {
			result = ResponseEntity.notFound().build();
		} else {
			result = ResponseEntity.ok(control);
		}
		return result;
	}
	
}
