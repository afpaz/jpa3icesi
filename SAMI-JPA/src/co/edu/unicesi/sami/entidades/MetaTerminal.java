package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the metas_terminales database table.
 * 
 */
@Entity
@Table(name="metas_terminales")
public class MetaTerminal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to ObjetivoTerminal
    @ManyToOne
	@JoinColumn(name="id_obj_terminal")
	private ObjetivoTerminal objetivosTerminale;

	//bi-directional many-to-one association to Unidad
    @ManyToOne
	@JoinColumn(name="id_unidad")
	private Unidad unidade;

	//bi-directional many-to-one association to ObjetivoEspecifico
	@OneToMany(mappedBy="metasTerminale")
	private List<ObjetivoEspecifico> objetivosEspecificos;

    public MetaTerminal() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ObjetivoTerminal getObjetivosTerminale() {
		return this.objetivosTerminale;
	}

	public void setObjetivosTerminale(ObjetivoTerminal objetivosTerminale) {
		this.objetivosTerminale = objetivosTerminale;
	}
	
	public Unidad getUnidade() {
		return this.unidade;
	}

	public void setUnidade(Unidad unidade) {
		this.unidade = unidade;
	}
	
	public List<ObjetivoEspecifico> getObjetivosEspecificos() {
		return this.objetivosEspecificos;
	}

	public void setObjetivosEspecificos(List<ObjetivoEspecifico> objetivosEspecificos) {
		this.objetivosEspecificos = objetivosEspecificos;
	}
	
}