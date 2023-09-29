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
import es.unican.CIBEL.domain.Activo;
import es.unican.CIBEL.service.CategoriaService;
import es.unican.CIBEL.service.ActivoService;

@RestController
@RequestMapping("activos")
public class ActivoController {
	
	@Autowired
	private ActivoService activosService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public List<Activo> getActivos(@RequestParam(value="categoria", required = false) String categoria) {
		
		List<Activo> activos = new LinkedList<Activo>();
		
		if (categoria != null) {
			activos = activosService.activosPorCategoria(categoriaService.buscaCategoriaPorNombre(categoria));
		} else {
			activos = activosService.activos();
		}
		
		return activos;
	}
	
	@GetMapping("/apps")
	public List<Aplicacion> getAplicaciones(@RequestParam(value="categoria", required = false) String categoria) {
		
		List<Aplicacion> apps = new LinkedList<Aplicacion>();
		
		if (categoria != null) {
			apps = activosService.aplicacionesPorCategoria(categoriaService.buscaCategoriaPorNombre(categoria));
		} else {
			apps = activosService.aplicaciones();
		}
		
		return apps;
	}
	
	@GetMapping("/apps/{nombre}")
	public ResponseEntity<Aplicacion> getAplicacion(@PathVariable String nombre) {
		ResponseEntity<Aplicacion> result;
		Aplicacion app = activosService.buscaAplicacion(nombre);
		
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
			disps = activosService.dispositivosPorCategoria(categoriaService.buscaCategoriaPorNombre(categoria));
		} else {
			disps = activosService.dispositivos();
		}
		
		return disps;
	}
	
	@GetMapping("/dispositivos/{nombre}")
	public ResponseEntity<DispositivoIot> getDispositivo(@PathVariable String nombre) {
		ResponseEntity<DispositivoIot> result;
		DispositivoIot disp = activosService.buscaDispositivo(nombre);
		
		if (disp == null) {
			result = ResponseEntity.notFound().build();
		} else {
			result = ResponseEntity.ok(disp);
		}
		return result;
	}
}
