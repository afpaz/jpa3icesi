package co.edu.unicesi.sami.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the objetivos_especificos database table.
 * 
 */
@Entity
@Table(name="objetivos_especificos")
public class ObjetivoEspecifico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String contenido;

	private String nombre;

	//bi-directional many-to-one association to MetaTerminal
    @ManyToOne
	@JoinColumn(name="id_meta_terminal")
	private MetaTerminal metasTerminale;

	//bi-directional many-to-one association to Saber
	@OneToMany(mappedBy="objetivosEspecifico")
	private List<Saber> saberes;

    public ObjetivoEspecifico() {
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

	public MetaTerminal getMetasTerminale() {
		return this.metasTerminale;
	}

	public void setMetasTerminale(MetaTerminal metasTerminale) {
		this.metasTerminale = metasTerminale;
	}
	
	public List<Saber> getSaberes() {
		return this.saberes;
	}

	public void setSaberes(List<Saber> saberes) {
		this.saberes = saberes;
	}
	
}