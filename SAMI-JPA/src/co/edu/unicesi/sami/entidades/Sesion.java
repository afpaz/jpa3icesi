package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sesiones database table.
 * 
 */
@Entity
@Table(name="sesiones")
public class Sesion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nombre;

	private int numero;

	//bi-directional many-to-one association to Unidad
    @ManyToOne
	@JoinColumn(name="id_unidad")
	private Unidad unidade;

	//bi-directional many-to-one association to TrabajoAsignado
	@OneToMany(mappedBy="sesione")
	private List<TrabajoAsignado> trabajosAsignados;

    public Sesion() {
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

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Unidad getUnidade() {
		return this.unidade;
	}

	public void setUnidade(Unidad unidade) {
		this.unidade = unidade;
	}
	
	public List<TrabajoAsignado> getTrabajosAsignados() {
		return this.trabajosAsignados;
	}

	public void setTrabajosAsignados(List<TrabajoAsignado> trabajosAsignados) {
		this.trabajosAsignados = trabajosAsignados;
	}
	
}