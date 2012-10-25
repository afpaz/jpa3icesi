package co.edu.unicesi.sami.server;


import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

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
import co.edu.unicesi.sami.client.listados.ListadosService;
import co.edu.unicesi.sami.listados.GestionListadosRemote;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ListadosServiceImpl extends RemoteServiceServlet implements ListadosService
{
    
    private InitialContext context;
    private GestionListadosRemote listadosService;
    
    public ListadosServiceImpl( )
    {
        try
        {
            context = new InitialContext( );
            listadosService = (GestionListadosRemote) context.lookup( "java:global/SAMI-EAR/SAMI-EJB/GestionListadosBean!co.edu.unicesi.sami.listados.GestionListadosRemote" );
        }
        catch( NamingException e )
        {
            e.printStackTrace( );
        }
    }
    
    @Override
    public List<MaterialBO> listarMaterialesPorCurso( int idCurso )
    {
        return listadosService.listarMaterialesPorCurso( idCurso );
    }

    @Override
    public List<MetaTerminalBO> listarMetasTerminalesPorCurso( int idCurso )
    {
        return listadosService.listarMetasTerminalesPorCurso( idCurso );
    }

    @Override
    public List<ObjetivoEspecificoBO> listarObjEspecificosPorCurso( int idCurso )
    {
        return listadosService.listarObjEspecificosPorCurso( idCurso );
    }

    @Override
    public List<ObjetivoEspecificoBO> listarObjEspecificosPorMetaTerminal( int idMetaTerminal )
    {
        return listadosService.listarObjEspecificosPorMetaTerminal( idMetaTerminal );
    }

    @Override
    public List<ObjetivoEspecificoBO> listarObjEspecificosPorUnidad( int idUnidad )
    {
        return listadosService.listarObjEspecificosPorUnidad( idUnidad );
    }

    @Override
    public List<ObjetivoTerminalBO> listarObjTerminalesPorCurso( int idCurso )
    {
        return listadosService.listarObjTerminalesPorCurso( idCurso );
    }

    @Override
    public List<RecursoBO> listarRecursosPorSaber( int idSaber )
    {
        return listadosService.listarRecursosPorSaber( idSaber );
    }

    @Override
    public List<SaberBO> listarSaberesPorCurso(int idCurso)
    {
        return listadosService.listarSaberesPorCurso( idCurso );
    }
    
    @Override
    public List<SaberBO> listarSaberesPorObjetivoEspecifico( int idObjEspecifico )
    {
        return listadosService.listarSaberesPorObjetivoEspecifico( idObjEspecifico );
    }

    @Override
    public List<SesionBO> listarSesionesPorUnidad( int idUnidad )
    {
        return listadosService.listarSesionesPorUnidad( idUnidad );
    }

    @Override
    public List<TrabajoAsignadoBO> listarTrabajosAsignadosPorSesion( int idSesion )
    {
        return listadosService.listarTrabajosAsignadosPorSesion( idSesion );
    }

    @Override
    public List<RecursoAsignadoBO> listarRecursosAsignadosPorTrabajoAsignado( int idTrabajoAsignado )
    {
        return listadosService.listarRecursosAsignadosPorTrabajoAsignado( idTrabajoAsignado );
    }

    @Override
    public List<UnidadBO> listarUnidadesPorCurso( int idCurso )
    {
        return listadosService.listarUnidadesPorCurso( idCurso );
    }
	
}
