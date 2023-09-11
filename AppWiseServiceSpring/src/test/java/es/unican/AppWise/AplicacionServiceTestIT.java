package es.unican.AppWise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import es.unican.AppWise.domain.Aplicacion;
import es.unican.AppWise.domain.Categoria;
import es.unican.AppWise.repository.AplicacionRepository;
import es.unican.AppWise.service.AplicacionService;
import es.unican.AppWise.service.CategoriaService;

@SpringBootTest
@Transactional
public class AplicacionServiceTestIT {
//	@Autowired
//	private AplicacionService sut;
//	
//	@Autowired
//	private AplicacionRepository repository;
//	
//	@Autowired
//	private CategoriaService catService;
//	
//	@Test
//	public void testAplicacionesExistentes() {
//		// IAS.1a
//		List<Aplicacion> apps = sut.aplicaciones();
//		assertTrue(apps.size() >= 50);
//		
//		// IAS.1b
//		repository.deleteAll();
//		assertTrue(sut.aplicaciones().isEmpty());
//	}
//	
//	@Test
//	public void testAplicacionesPorCategoria() {
//		// IAS.2a
//		Categoria c = catService.buscaCategoriaPorNombre("Redes Sociales");
//		List<Aplicacion> apps = sut.aplicacionesPorCategoria(c);
//		assertTrue(apps.size() >= 5);
//		
//		// IAS.2b
//		assertEquals(Collections.emptyList(), sut.aplicacionesPorCategoria(null));
//	}
//	
//	@Test
//	public void testBuscaAplicacion() {
//		// IAS.3a
//		Aplicacion app = sut.buscaAplicacion("Facebook");
//		assertTrue(app != null);
//		assertEquals("Facebook", app.getNombre());
//		
//		// IAS.3b
//		assertNull(sut.buscaAplicacion("XXX"));
//	}
}
