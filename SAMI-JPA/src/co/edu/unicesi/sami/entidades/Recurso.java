package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the recursos database table.
 * 
 */
@Entity
@Table(name="recursos")
public class Recurso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Material
    @ManyToOne
	@JoinColumn(name="id_material")
	private Material materiale;

	//bi-directional many-to-one association to Saber
    @ManyToOne
	@JoinColumn(name="id_saber")
	private Saber sabere;

	//bi-directional many-to-one association to RecursoAsignado
	@OneToMany(mappedBy="recurso")
	private List<RecursoAsignado> recursosAsignados;

    public Recurso() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Material getMateriale() {
		return this.materiale;
	}

	public void setMateriale(Material materiale) {
		this.materiale = materiale;
	}
	
	public Saber getSabere() {
		return this.sabere;
	}

	public void setSabere(Saber sabere) {
		this.sabere = sabere;
	}
	
	public List<RecursoAsignado> getRecursosAsignados() {
		return this.recursosAsignados;
	}

	public void setRecursosAsignados(List<RecursoAsignado> recursosAsignados) {
		this.recursosAsignados = recursosAsignados;
	}
	
}