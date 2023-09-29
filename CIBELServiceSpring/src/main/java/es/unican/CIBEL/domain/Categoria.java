package es.unican.CIBEL.domain;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.JoinColumn;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	private String tipo;
	
	@ManyToMany
	@JoinTable(name = "categoria_x_control",
				joinColumns = @JoinColumn(name = "fk_categoria"),
				inverseJoinColumns = @JoinColumn(name = "fk_control"))
	private List<Control> controles;
	
	public Categoria() {}
	
	public Categoria(String nombre, String tipo) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.controles = new LinkedList<Control>();
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Control> getControles() {
		return controles;
	}

	public void setControles(List<Control> controles) {
		this.controles = controles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
