package es.unican.CIBEL.domain;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Entity
public class Amenaza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	@Column(length = 2000)
	private String descripcion;
	
	private String tipo;
	
	@ManyToMany
	@JoinTable(name = "control_x_riesgo",
				joinColumns = @JoinColumn(name = "fk_riesgo"),
				inverseJoinColumns = @JoinColumn(name = "fk_control"))
	@JsonManagedReference
	private List<Control> controles;
	
	public Amenaza() {}
	
	public Amenaza(String nombre, String descripcion, String tipo) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.controles = new LinkedList<Control>();
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public List<Control> getControles() {
		return controles;
	}
	
	public void setControles(List<Control> controles) {
		this.controles = controles;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
