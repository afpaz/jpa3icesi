package co.edu.unicesi.sami.client.model;

import java.io.Serializable;
import java.util.List;

import co.edu.unicesi.sami.bo.UnidadBO;

import com.extjs.gxt.ui.client.data.BaseModel;

public class UnidadModel extends BaseModel implements Serializable {

	public UnidadModel() {

	}

	public UnidadModel(int id, int numero, String nombre, String contenido) {
		set("id", id);
		set("numero", numero);
		set("nombre", nombre);
		set("contenido", contenido);
	}

	public int getId() {
		return (Integer) get("id");
	}

	public int getNumero() {
		return (Integer) get("numero");
	}

	public String getNombre() {
		return (String) get("nombre");
	}

	public String getContenido() {
		return (String) get("contenido");
	}

	public void setId(int id) {
		set("id", id);
	}

	public void setNumero(int numero) {
		set("numero", numero);
	}

	public void setNombre(String nombre) {
		set("nombre", nombre);
	}

	public void setContenido(String contenido) {
		set("contenido", contenido);
	}

	public static UnidadModel toModelFromBO(UnidadBO bo) {
		// TODO Auto-generated method stub
		UnidadModel uModel = new UnidadModel();
		uModel.setId(bo.getId());
		uModel.setNombre(bo.getNombre());
		uModel.setContenido(bo.getContenido());
		uModel.setNumero(bo.getNumero());
		return uModel;
	}
}
