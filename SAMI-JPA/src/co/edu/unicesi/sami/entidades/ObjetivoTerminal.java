package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the objetivos_terminales database table.
 * 
 */
@Entity
@Table(name="objetivos_terminales")
public class ObjetivoTerminal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String contenido;

	private String nombre;

	//bi-directional many-to-one association to MetaTerminal
	@OneToMany(mappedBy="objetivosTerminale")
	private List<MetaTerminal> metasTerminales;

	//bi-directional many-to-one association to Curso
    @ManyToOne
	@JoinColumn(name="id_curso")
	private Curso curso;

    public ObjetivoTerminal() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenido() {
		return this.contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<MetaTerminal> getMetasTerminales() {
		return this.metasTerminales;
	}

	public void setMetasTerminales(List<MetaTerminal> metasTerminales) {
		this.metasTerminales = metasTerminales;
	}
	
	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
}