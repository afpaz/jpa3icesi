package co.edu.unicesi.sami.client.model;

import java.io.Serializable;
import java.util.List;

import co.edu.unicesi.sami.bo.SaberBO;

import com.extjs.gxt.ui.client.data.BaseModel;

public class SaberModel extends BaseModel implements Serializable {
	private int id;
	private String nombre;
	private String contenido;
	private String tipo;

	public SaberModel() {

	}

	public SaberModel(int id, String nombre, String contenido, String tipo) {
		set("id", id);
		set("nombre", nombre);
		set("contenido", contenido);
		set("tipo", tipo);
	}

	public int getId() {
		return (Integer) get("id");
	}

	public String getNombre() {
		return (String) get("nombre");
	}

	public String getContenido() {
		return (String) get("contenido");
	}

	public String getTipo() {
		return (String) get("tipo");
	}

	public void setId(int id) {
		set("id", id);
	}

	public void setNombre(String nombre) {
		set("nombre", nombre);
	}

	public void setContenido(String contenido) {
		set("contenido", contenido);
	}

	public void setTipo(String tipo) {
		set("tipo", tipo);
	}

	public static SaberModel toModelFromBO(SaberBO bo) {
		SaberModel sModel = new SaberModel();
		sModel.setId(bo.getId());
		sModel.setNombre(bo.getNombre());
		sModel.setContenido(bo.getContenido());

		if (bo.getTipo().equalsIgnoreCase("C")) {
			sModel.setTipo("Conocer");

		} else {
			sModel.setTipo("Hacer");
		}

		return sModel;
	}

}
