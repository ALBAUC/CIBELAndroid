package es.unican.CIBEL.domain;

import javax.persistence.Entity;

@Entity
public class DispositivoIot extends Activo {
	
	public DispositivoIot() {}

	public DispositivoIot(String nombre, String icono, Categoria categoria) {
		super(nombre, icono, categoria);
		// TODO Auto-generated constructor stub
	}

}
