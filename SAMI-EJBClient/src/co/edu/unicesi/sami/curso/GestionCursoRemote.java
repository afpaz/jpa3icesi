package co.edu.unicesi.sami.curso;

import java.util.List;
import javax.ejb.Remote;
import co.edu.unicesi.sami.bo.CursoBO;

@Remote
public interface GestionCursoRemote {
	public int agregarCurso(CursoBO curso);
    public int editarCurso(CursoBO curso);
    public CursoBO buscarCurso(int idCurso);
    public List<CursoBO> listarCursos();
}
