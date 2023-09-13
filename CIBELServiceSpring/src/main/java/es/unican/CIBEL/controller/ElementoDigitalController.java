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

import es.unican.CIBEL.domain.ElementoDigital;
import es.unican.CIBEL.service.CategoriaService;
import es.unican.CIBEL.service.ElementoDigitalService;

@RestController
@RequestMapping("elementosDigitales")
public class ElementoDigitalController {
	
	@Autowired
	private ElementoDigitalService elementosDService;
	
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
	
	@GetMapping("/{nombre}")
	public ResponseEntity<ElementoDigital> getElementoDigital(@PathVariable String nombre) {
		ResponseEntity<ElementoDigital> result;
		ElementoDigital ele = elementosDService.buscaElementoDigital(nombre);
		
		if (ele == null) {
			result = ResponseEntity.notFound().build();
		} else {
			result = ResponseEntity.ok(ele);
		}
		return result;
	}
}
