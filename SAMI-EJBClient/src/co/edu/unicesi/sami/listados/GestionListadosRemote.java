package co.edu.unicesi.sami.listados;

import java.util.List;

import javax.ejb.Remote;

import co.edu.unicesi.sami.bo.MaterialBO;
import co.edu.unicesi.sami.bo.MetaTerminalBO;
import co.edu.unicesi.sami.bo.ObjetivoEspecificoBO;
import co.edu.unicesi.sami.bo.ObjetivoTerminalBO;
import co.edu.unicesi.sami.bo.RecursoAsignadoBO;
import co.edu.unicesi.sami.bo.RecursoBO;
import co.edu.unicesi.sami.bo.SaberBO;
import co.edu.unicesi.sami.bo.SesionBO;
import co.edu.unicesi.sami.bo.TrabajoAsignadoBO;
import co.edu.unicesi.sami.bo.UnidadBO;

@Remote
public interface GestionListadosRemote {
	public List<MaterialBO> listarMaterialesPorCurso(int idCurso);
    public List<MetaTerminalBO> listarMetasTerminalesPorCurso(int idCurso);
    public List<ObjetivoEspecificoBO> listarObjEspecificosPorCurso(int idCurso);
    public List<ObjetivoEspecificoBO> listarObjEspecificosPorMetaTerminal(int idMetaTerminal);    
    public List<ObjetivoEspecificoBO> listarObjEspecificosPorUnidad(int idUnidad);
    public List<ObjetivoTerminalBO> listarObjTerminalesPorCurso(int idCurso);
    public List<RecursoBO> listarRecursosPorSaber(int idSaber);
    public List<SaberBO> listarSaberesPorCurso(int idCurso);
    public List<SaberBO> listarSaberesPorObjetivoEspecifico(int idObjEspecifico);
    public List<SesionBO> listarSesionesPorUnidad(int idUnidad);
    public List<TrabajoAsignadoBO> listarTrabajosAsignadosPorSesion(int idSesion);
    public List<RecursoAsignadoBO> listarRecursosAsignadosPorTrabajoAsignado(int idTrabajoAsignado);
    public List<UnidadBO> listarUnidadesPorCurso(int idCurso);
}
