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
	
	@GetMapping("/{nombre}")
	public ResponseEntity<Activo> getActivo(@PathVariable String nombre) {
		ResponseEntity<Activo> result;
		Activo activo = activosService.buscaActivo(nombre);
		
		if (activo == null) {
			result = ResponseEntity.notFound().build();
		} else {
			result = ResponseEntity.ok(activo);
		}
		return result;
	}
}
