package es.unican.CIBEL.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Activo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	@Column(length = 500)
	private String icono;
	
	@OneToOne
	@JoinColumn(name="fk_categoria")
	private Categoria categoria;
	
	public Activo() {}
	
	public Activo(String nombre, String icono, Categoria categoria) {
		this.nombre = nombre;
		this.icono = icono;
		this.categoria = categoria;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getIcono() {
		return icono;
	}
	
	public void setIcono(String icono) {
		this.icono = icono;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria, icono, id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activo other = (Activo) obj;
		return Objects.equals(categoria, other.categoria) && Objects.equals(icono, other.icono)
				&& Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Activo [id=" + id + ", nombre=" + nombre + ", icono=" + icono + ", categoria=" + categoria.getNombre() + "]";
	}
}
