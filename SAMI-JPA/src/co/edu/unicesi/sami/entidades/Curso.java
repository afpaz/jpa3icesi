package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cursos database table.
 * 
 */
@Entity
@Table(name="cursos")
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String codigo;

	private String nombre;

	//bi-directional many-to-one association to Material
	@OneToMany(mappedBy="curso")
	private List<Material> materiales;

	//bi-directional many-to-one association to ObjetivoGeneral
	@OneToMany(mappedBy="curso")
	private List<ObjetivoGeneral> objetivosGenerales;

	//bi-directional many-to-one association to ObjetivoTerminal
	@OneToMany(mappedBy="curso")
	private List<ObjetivoTerminal> objetivosTerminales;

	//bi-directional many-to-one association to Unidad
	@OneToMany(mappedBy="curso")
	private List<Unidad> unidades;

    public Curso() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Material> getMateriales() {
		return this.materiales;
	}

	public void setMateriales(List<Material> materiales) {
		this.materiales = materiales;
	}
	
	public List<ObjetivoGeneral> getObjetivosGenerales() {
		return this.objetivosGenerales;
	}

	public void setObjetivosGenerales(List<ObjetivoGeneral> objetivosGenerales) {
		this.objetivosGenerales = objetivosGenerales;
	}
	
	public List<ObjetivoTerminal> getObjetivosTerminales() {
		return this.objetivosTerminales;
	}

	public void setObjetivosTerminales(List<ObjetivoTerminal> objetivosTerminales) {
		this.objetivosTerminales = objetivosTerminales;
	}
	
	public List<Unidad> getUnidades() {
		return this.unidades;
	}

	public void setUnidades(List<Unidad> unidades) {
		this.unidades = unidades;
	}
	
}