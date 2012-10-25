package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the recursos_asignados database table.
 * 
 */
@Entity
@Table(name="recursos_asignados")
public class RecursoAsignado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Recurso
    @ManyToOne
	@JoinColumn(name="id_recurso")
	private Recurso recurso;

	//bi-directional many-to-one association to TrabajoAsignado
    @ManyToOne
	@JoinColumn(name="id_trabajo_asignado")
	private TrabajoAsignado trabajosAsignado;

    public RecursoAsignado() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Recurso getRecurso() {
		return this.recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}
	
	public TrabajoAsignado getTrabajosAsignado() {
		return this.trabajosAsignado;
	}

	public void setTrabajosAsignado(TrabajoAsignado trabajosAsignado) {
		this.trabajosAsignado = trabajosAsignado;
	}
	
}