package co.edu.unicesi.sami.server;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.edu.unicesi.sami.bo.MaterialBO;
import co.edu.unicesi.sami.bo.RecursoAsignadoBO;
import co.edu.unicesi.sami.bo.RecursoBO;
import co.edu.unicesi.sami.bo.SaberBO;
import co.edu.unicesi.sami.bo.SesionBO;
import co.edu.unicesi.sami.bo.TrabajoAsignadoBO;
import co.edu.unicesi.sami.client.planificador.PlanificadorService;
import co.edu.unicesi.sami.planificador.GestionPlanificadorRemote;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PlanificadorServiceImpl extends RemoteServiceServlet implements PlanificadorService
{

    private InitialContext context;
    private GestionPlanificadorRemote planificadorService;

    public PlanificadorServiceImpl( )
    {
        try
        {
            context = new InitialContext( );
            planificadorService = ( GestionPlanificadorRemote )context.lookup( "java:global/SAMI-EAR/SAMI-EJB/GestionPlanificadorBean!co.edu.unicesi.sami.planificador.GestionPlanificadorRemote" );
        }
        catch( NamingException e )
        {
            e.printStackTrace( );
        }
    }

    @Override
    public int agregarMaterial( MaterialBO material )
    {
        return planificadorService.agregarMaterial( material );
    }

    @Override
    public int editarMaterial( MaterialBO material )
    {
        return planificadorService.editarMaterial( material );
    }

    @Override
    public MaterialBO buscarMaterial( int idMaterial )
    {
        return planificadorService.buscarMaterial( idMaterial );
    }

    @Override
    public int agregarSaber( SaberBO saber )
    {
        return planificadorService.agregarSaber( saber );
    }

    @Override
    public int editarSaber( SaberBO saber )
    {
        return planificadorService.editarSaber( saber );
    }

    @Override
    public SaberBO buscarSaber( int idSaber )
    {
        return planificadorService.buscarSaber( idSaber );
    }

    @Override
    public int agregarRecurso( RecursoBO recurso )
    {
        return planificadorService.agregarRecurso( recurso );
    }

    @Override
    public RecursoBO buscarRecurso( int idRecurso )
    {
        return planificadorService.buscarRecurso( idRecurso );
    }

    @Override
    public int eliminarRecurso( RecursoBO recurso )
    {
        return planificadorService.eliminarRecurso( recurso );
    }

    @Override
    public int agregarSesion( SesionBO sesion )
    {
       return planificadorService.agregarSesion( sesion );
    }

    @Override
    public int editarSesion( SesionBO sesion )
    {
        return planificadorService.editarSesion( sesion );
    }

    @Override
    public SesionBO buscarSesion( int idSesion )
    {
        return planificadorService.buscarSesion( idSesion );
    }

    @Override
    public int agregarTrabajoAsignado( TrabajoAsignadoBO trabajoAsignado )
    {
        return planificadorService.agregarTrabajoAsignado( trabajoAsignado );
    }

    @Override
    public int editarTrabajoAsignado( TrabajoAsignadoBO trabajoAsignado )
    {
        return planificadorService.editarTrabajoAsignado( trabajoAsignado );
    }

    @Override
    public TrabajoAsignadoBO buscarTrabajoAsignado( int idTrabajoAsignado )
    {
        return planificadorService.buscarTrabajoAsignado( idTrabajoAsignado );
    }

    @Override
    public int agregarRecursoAsignado( RecursoAsignadoBO recursoAsignado )
    {
        return planificadorService.agregarRecursoAsignado( recursoAsignado );
    }

    @Override
    public int eliminarRecursoAsignado( RecursoAsignadoBO recursoAsignado )
    {
        return planificadorService.eliminarRecursoAsignado( recursoAsignado );
    }

    @Override
    public RecursoAsignadoBO buscarRecursoAsignado( int idRecursoAsignado )
    {
        return planificadorService.buscarRecursoAsignado( idRecursoAsignado );
    }

}
