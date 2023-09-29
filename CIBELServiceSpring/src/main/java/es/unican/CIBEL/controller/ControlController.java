package es.unican.CIBEL.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.unican.CIBEL.domain.Control;
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
	public List<Control> getControlesDeApps() {
		return controlService.controlesDeApps();
	}
	
	@GetMapping("/dispositivos")
	public List<Control> getControlesDeDispositivos() {
		return controlService.controlesDeDispositivos();
	}
}
