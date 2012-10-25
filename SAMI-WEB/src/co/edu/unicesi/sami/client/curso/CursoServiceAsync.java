package co.edu.unicesi.sami.client.curso;

import java.util.List;

import co.edu.unicesi.sami.bo.CursoBO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CursoServiceAsync
{
    public void agregarCurso(CursoBO curso, AsyncCallback<Integer> callback);
    public void editarCurso(CursoBO curso, AsyncCallback<Integer> callback);
    public void buscarCurso(int idCurso, AsyncCallback<CursoBO> callback);
    public void listarCursos(AsyncCallback<List<CursoBO>> callback);
}
