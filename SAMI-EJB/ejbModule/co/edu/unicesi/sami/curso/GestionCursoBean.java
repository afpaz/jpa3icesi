package co.edu.unicesi.sami.curso;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.unicesi.sami.bo.CursoBO;
import co.edu.unicesi.sami.bo.MetaTerminalBO;
import co.edu.unicesi.sami.entidades.Curso;
import co.edu.unicesi.sami.entidades.Material;
import co.edu.unicesi.sami.entidades.MetaTerminal;
import co.edu.unicesi.sami.entidades.ObjetivoGeneral;
import co.edu.unicesi.sami.entidades.ObjetivoTerminal;
import co.edu.unicesi.sami.entidades.Saber;
import co.edu.unicesi.sami.entidades.Unidad;

/**
 * Session Bean implementation class GestionCursoBean
 */
@Stateless
public class GestionCursoBean implements GestionCursoRemote, GestionCursoLocal {

	@PersistenceContext
    EntityManager em;

    @Override
    public int agregarCurso( CursoBO curso )
    {
        Curso entidad = new Curso( );
        int resp = -1;
        try
        {
            entidad.setCodigo( curso.getCodigo( ) );
            entidad.setNombre( curso.getNombre( ) );
            entidad.setObjetivosGenerales( new ArrayList<ObjetivoGeneral>( ) );
            entidad.setUnidades( new ArrayList<Unidad>( ) );
            entidad.setObjetivosTerminales( new ArrayList<ObjetivoTerminal>( ) );
            
            em.persist( entidad );
            em.flush( );
            resp = 0;
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }

        return resp;

    }

    @Override
    public int editarCurso( CursoBO curso )
    {
        int resp = -1;
        try
        {
            Curso entidad = em.find( Curso.class, curso.getId( ) );

            entidad.setNombre( curso.getNombre( ) );
            entidad.setCodigo( curso.getCodigo( ) );

            em.merge( entidad );
            em.flush( );
            resp = 1;
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }

        return resp;
    }

    @Override
    public CursoBO buscarCurso( int idCurso )
    {
        Curso curso = em.find( Curso.class, idCurso );
        em.refresh( curso );

        CursoBO bo = new CursoBO( );

        bo.setId( idCurso );
        bo.setCodigo( curso.getCodigo( ) );
        bo.setNombre( curso.getNombre( ) );

        if( curso.getObjetivosGenerales( ).size( ) > 0 )
        {
            bo.setIdObjGeneral( curso.getObjetivosGenerales( ).get( 0 ).getId( ) );
            bo.setContenidoObjGeneral( curso.getObjetivosGenerales( ).get( 0 ).getContenido( ) );
        }

        List<Integer> unidades = new ArrayList<Integer>( );
        for( Unidad u : curso.getUnidades( ) )
        {
            unidades.add( u.getId( ) );
        }
        bo.setUnidades( unidades );

        List<Integer> objTerminales = new ArrayList<Integer>( );
        for( ObjetivoTerminal ot : curso.getObjetivosTerminales( ) )
        {
            objTerminales.add( ot.getId( ) );
        }
        bo.setObjTerminales( objTerminales );

        List<Integer> materiales = new ArrayList<Integer>( );
        for( Material m : curso.getMateriales( ) )
        {
            materiales.add( m.getId( ) );
        }
        bo.setMateriales( materiales );

        return bo;
    }

    @Override
    public List<CursoBO> listarCursos( )
    {
        String query = "SELECT c FROM Curso c";
        Query q = em.createQuery( query );
        List<Curso> cursos = q.getResultList( );

        List<CursoBO> cursosBO = new ArrayList<CursoBO>( );

        for( Curso c : cursos )
        {
            CursoBO bo = new CursoBO( );

            bo.setId( c.getId( ) );
            bo.setCodigo( c.getCodigo( ) );
            bo.setNombre( c.getNombre( ) );

            cursosBO.add( bo );
        }

        return cursosBO;
    }
}
