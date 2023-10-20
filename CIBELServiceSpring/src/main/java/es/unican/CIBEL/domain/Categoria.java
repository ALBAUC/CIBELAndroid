package es.unican.CIBEL.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
	
	@ManyToMany
	@JoinTable(name = "categoria_x_control",
				joinColumns = @JoinColumn(name = "fk_categoria"),
				inverseJoinColumns = @JoinColumn(name = "fk_control"))
	private List<Control> controles;
	
	public Categoria() {}
	
	public Categoria(String nombre) {
		this.nombre = nombre;
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
	@Override
	public int hashCode() {
		return Objects.hash(controles, id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return Objects.equals(controles, other.controles) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre);
	}
}
