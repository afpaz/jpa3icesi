package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the saberes database table.
 * 
 */
@Entity
@Table(name="saberes")
public class Saber implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String contenido;

	private String nombre;

	private String tipo;

	//bi-directional many-to-one association to Recurso
	@OneToMany(mappedBy="sabere")
	private List<Recurso> recursos;

	//bi-directional many-to-one association to ObjetivoEspecifico
    @ManyToOne
	@JoinColumn(name="id_obj_especifico")
	private ObjetivoEspecifico objetivosEspecifico;

    public Saber() {
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

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Recurso> getRecursos() {
		return this.recursos;
	}

	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}
	
	public ObjetivoEspecifico getObjetivosEspecifico() {
		return this.objetivosEspecifico;
	}

	public void setObjetivosEspecifico(ObjetivoEspecifico objetivosEspecifico) {
		this.objetivosEspecifico = objetivosEspecifico;
	}
	
}