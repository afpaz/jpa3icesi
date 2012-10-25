package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the materiales database table.
 * 
 */
@Entity
@Table(name="materiales")
public class Material implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nombre;

	//bi-directional many-to-one association to Curso
    @ManyToOne
	@JoinColumn(name="id_cursos")
	private Curso curso;

	//bi-directional many-to-one association to Recurso
	@OneToMany(mappedBy="materiale")
	private List<Recurso> recursos;

    public Material() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public List<Recurso> getRecursos() {
		return this.recursos;
	}

	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}
	
}