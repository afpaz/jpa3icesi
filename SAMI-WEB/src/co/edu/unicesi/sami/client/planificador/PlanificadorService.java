package co.edu.unicesi.sami.client.planificador;

import co.edu.unicesi.sami.bo.MaterialBO;
import co.edu.unicesi.sami.bo.RecursoAsignadoBO;
import co.edu.unicesi.sami.bo.RecursoBO;
import co.edu.unicesi.sami.bo.SaberBO;
import co.edu.unicesi.sami.bo.SesionBO;
import co.edu.unicesi.sami.bo.TrabajoAsignadoBO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("PlanificadorService")
public interface PlanificadorService extends RemoteService
{
    public int agregarMaterial(MaterialBO material);
    public int editarMaterial(MaterialBO material);
    public MaterialBO buscarMaterial(int idMaterial);
    
    public int agregarSaber(SaberBO saber);
    public int editarSaber(SaberBO saber);
    public SaberBO buscarSaber(int idSaber);
    
    public int agregarRecurso(RecursoBO recurso);
    public RecursoBO buscarRecurso(int idRecurso);
    public int eliminarRecurso(RecursoBO recurso);
    
    public int agregarSesion(SesionBO sesion);
    public int editarSesion(SesionBO sesion);
    public SesionBO buscarSesion(int idSesion);
    
    public int agregarTrabajoAsignado(TrabajoAsignadoBO trabajoAsignado);
    public int editarTrabajoAsignado(TrabajoAsignadoBO trabajoAsignado);
    public TrabajoAsignadoBO buscarTrabajoAsignado(int idTrabajoAsignado);
    
    public int agregarRecursoAsignado(RecursoAsignadoBO recursoAsignado);
    public int eliminarRecursoAsignado(RecursoAsignadoBO recursoAsignado);
    public RecursoAsignadoBO buscarRecursoAsignado(int idRecursoAsignado);
}
