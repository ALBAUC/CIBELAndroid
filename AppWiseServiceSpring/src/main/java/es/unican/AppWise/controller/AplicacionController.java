package es.unican.AppWise.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.unican.AppWise.domain.Aplicacion;
import es.unican.AppWise.service.AplicacionService;
import es.unican.AppWise.service.CategoriaService;

@RestController
@RequestMapping("/apps")
public class AplicacionController {
	
	@Autowired
	private AplicacionService aplicacionService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public List<Aplicacion> getAplicaciones(@RequestParam(value="categoria", required = false) String categoria) {
		
		List<Aplicacion> apps = new LinkedList<Aplicacion>();
		
		if (categoria != null) {
			apps = aplicacionService.aplicacionesPorCategoria(categoriaService.buscaCategoriaPorNombre(categoria));
		} else {
			apps = aplicacionService.aplicaciones();
		}
		
		return apps;
	}
	
	@GetMapping("/{nombre}")
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
	
}
