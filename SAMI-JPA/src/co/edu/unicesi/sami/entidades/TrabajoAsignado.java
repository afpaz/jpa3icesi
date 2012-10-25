package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the trabajos_asignados database table.
 * 
 */
@Entity
@Table(name="trabajos_asignados")
public class TrabajoAsignado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String contenido;

	private String encargado;

	private String tipo;

	//bi-directional many-to-one association to RecursoAsignado
	@OneToMany(mappedBy="trabajosAsignado")
	private List<RecursoAsignado> recursosAsignados;

	//bi-directional many-to-one association to Sesion
    @ManyToOne
	@JoinColumn(name="id_sesion")
	private Sesion sesione;

    public TrabajoAsignado() {
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

	public String getEncargado() {
		return this.encargado;
	}

	public void setEncargado(String encargado) {
		this.encargado = encargado;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<RecursoAsignado> getRecursosAsignados() {
		return this.recursosAsignados;
	}

	public void setRecursosAsignados(List<RecursoAsignado> recursosAsignados) {
		this.recursosAsignados = recursosAsignados;
	}
	
	public Sesion getSesione() {
		return this.sesione;
	}

	public void setSesione(Sesion sesione) {
		this.sesione = sesione;
	}
	
}