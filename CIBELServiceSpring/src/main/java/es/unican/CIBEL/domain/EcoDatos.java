package es.unican.CIBEL.domain;

import javax.persistence.Embeddable;

@Embeddable
public class EcoDatos {
	
	private int durabilidad;
	private int reparabilidad;
	private int reciclabilidad;
	private int efClimatica;
	private int efRecursos;
	private int ecoPuntuacion;
	
	public EcoDatos() {}
	
	public EcoDatos(int durabilidad, int reparabilidad, int reciclabilidad, int efClimatica, int efRecursos,
			int ecoPuntuacion) {
		this.durabilidad = durabilidad;
		this.reparabilidad = reparabilidad;
		this.reciclabilidad = reciclabilidad;
		this.efClimatica = efClimatica;
		this.efRecursos = efRecursos;
		this.ecoPuntuacion = ecoPuntuacion;
	}

	public int getDurabilidad() {
		return durabilidad;
	}

	public void setDurabilidad(int durabilidad) {
		this.durabilidad = durabilidad;
	}

	public int getReparabilidad() {
		return reparabilidad;
	}

	public void setReparabilidad(int reparabilidad) {
		this.reparabilidad = reparabilidad;
	}

	public int getReciclabilidad() {
		return reciclabilidad;
	}

	public void setReciclabilidad(int reciclabilidad) {
		this.reciclabilidad = reciclabilidad;
	}

	public int getEfClimatica() {
		return efClimatica;
	}

	public void setEfClimatica(int efClimatica) {
		this.efClimatica = efClimatica;
	}

	public int getEfRecursos() {
		return efRecursos;
	}

	public void setEfRecursos(int efRecursos) {
		this.efRecursos = efRecursos;
	}

	public int getEcoPuntuacion() {
		return ecoPuntuacion;
	}

	public void setEcoPuntuacion(int ecoPuntuacion) {
		this.ecoPuntuacion = ecoPuntuacion;
	}
	
	
	

}
