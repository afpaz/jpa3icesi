package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the unidades database table.
 * 
 */
@Entity
@Table(name="unidades")
public class Unidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String contenido;

	private String nombre;

	private int numero;

	//bi-directional many-to-one association to MetaTerminal
	@OneToMany(mappedBy="unidade")
	private List<MetaTerminal> metasTerminales;

	//bi-directional many-to-one association to Sesion
	@OneToMany(mappedBy="unidade")
	private List<Sesion> sesiones;

	//bi-directional many-to-one association to Curso
    @ManyToOne
	@JoinColumn(name="id_curso")
	private Curso curso;

    public Unidad() {
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

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public List<MetaTerminal> getMetasTerminales() {
		return this.metasTerminales;
	}

	public void setMetasTerminales(List<MetaTerminal> metasTerminales) {
		this.metasTerminales = metasTerminales;
	}
	
	public List<Sesion> getSesiones() {
		return this.sesiones;
	}

	public void setSesiones(List<Sesion> sesiones) {
		this.sesiones = sesiones;
	}
	
	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
}