package co.edu.unicesi.sami.server;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.edu.unicesi.sami.bo.CursoBO;
import co.edu.unicesi.sami.client.curso.CursoService;
import co.edu.unicesi.sami.curso.GestionCursoRemote;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CursoServiceImpl extends RemoteServiceServlet implements CursoService
{

    private InitialContext context;
    private GestionCursoRemote cursoService;
    
    public CursoServiceImpl()
    {
        try
        {
            context = new InitialContext( );
            cursoService = (GestionCursoRemote) context.lookup("java:global/SAMI-EAR/SAMI-EJB/GestionCursoBean!co.edu.unicesi.sami.curso.GestionCursoRemote");
        }
        catch(NamingException e)
        {
            e.printStackTrace( );
        }
    }
    
    @Override
    public int agregarCurso( CursoBO curso )
    {
        return cursoService.agregarCurso( curso );
    }

    @Override
    public int editarCurso( CursoBO curso )
    {
        return cursoService.editarCurso( curso );
    }

    @Override
    public CursoBO buscarCurso( int idCurso )
    {
        return cursoService.buscarCurso( idCurso );
    }

    @Override
    public List<CursoBO> listarCursos( )
    {
        return cursoService.listarCursos( );
    }

}
