package es.unican.CIBEL.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.unican.CIBEL.domain.Aplicacion;
import es.unican.CIBEL.domain.DispositivoIot;
import es.unican.CIBEL.domain.ElementoDigital;
import es.unican.CIBEL.service.AplicacionService;
import es.unican.CIBEL.service.CategoriaService;
import es.unican.CIBEL.service.DispositivoIoTService;
import es.unican.CIBEL.service.ElementoDigitalService;

@RestController
@RequestMapping("elementosDigitales")
public class ElementoDigitalController {
	
	@Autowired
	private ElementoDigitalService elementosDService;
	
	@Autowired
	private AplicacionService aplicacionService;
	
	@Autowired
	private DispositivoIoTService dispositivosService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public List<ElementoDigital> getElementosDigitales(@RequestParam(value="categoria", required = false) String categoria) {
		
		List<ElementoDigital> elementos = new LinkedList<ElementoDigital>();
		
		if (categoria != null) {
			elementos = elementosDService.elementosDigitalesPorCategoria(categoriaService.buscaCategoriaPorNombre(categoria));
		} else {
			elementos = elementosDService.elementosDigitales();
		}
		
		return elementos;
	}
	
	@GetMapping("/apps")
	public List<Aplicacion> getAplicaciones(@RequestParam(value="categoria", required = false) String categoria) {
		
		List<Aplicacion> apps = new LinkedList<Aplicacion>();
		
		if (categoria != null) {
			apps = aplicacionService.aplicacionesPorCategoria(categoriaService.buscaCategoriaPorNombre(categoria));
		} else {
			apps = aplicacionService.aplicaciones();
		}
		
		return apps;
	}
	
	@GetMapping("/apps/{nombre}")
	public ResponseEntity<Aplicacion> getAplicacion(@PathVariable String nombre) {
		ResponseEntity<Aplicacion> result;
		Aplicacion app = aplicacionService.buscaAplicacion(nombre);
		
		if (app == null) {
			result = ResponseEntity.notFound().build();
		} else {
			result = ResponseEntity.ok(app);
		}
		return result;
	}
	
	@GetMapping("/dispositivos")
	public List<DispositivoIot> getDispositivos(@RequestParam(value="categoria", required = false) String categoria) {
		
		List<DispositivoIot> disps = new LinkedList<DispositivoIot>();
		
		if (categoria != null) {
			disps = dispositivosService.dispositivosPorCategoria(categoriaService.buscaCategoriaPorNombre(categoria));
		} else {
			disps = dispositivosService.dispositivos();
		}
		
		return disps;
	}
	
	@GetMapping("/dispositivos/{nombre}")
	public ResponseEntity<DispositivoIot> getDispositivo(@PathVariable String nombre) {
		ResponseEntity<DispositivoIot> result;
		DispositivoIot disp = dispositivosService.buscaDispositivo(nombre);
		
		if (disp == null) {
			result = ResponseEntity.notFound().build();
		} else {
			result = ResponseEntity.ok(disp);
		}
		return result;
	}
}
