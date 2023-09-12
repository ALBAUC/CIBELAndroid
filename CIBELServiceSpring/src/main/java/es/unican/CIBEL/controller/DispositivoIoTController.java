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

import es.unican.CIBEL.domain.DispositivoIot;
import es.unican.CIBEL.service.CategoriaService;
import es.unican.CIBEL.service.DispositivoIoTService;

@RestController
@RequestMapping("elementosDigitales/dispositivos")
public class DispositivoIoTController {
	
	@Autowired
	private DispositivoIoTService dispositivosService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public List<DispositivoIot> getDispositivos(@RequestParam(value="categoria", required = false) String categoria) {
		
		List<DispositivoIot> disps = new LinkedList<DispositivoIot>();
		
		if (categoria != null) {
			disps = dispositivosService.dispositivosPorCategoria(categoriaService.buscaCategoriaPorNombre(categoria));
		} else {
			disps = dispositivosService.dispositivos();
		}
		
		return disps;
	}
	
	@GetMapping("/{nombre}")
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
