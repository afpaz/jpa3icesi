package co.edu.unicesi.sami.listados;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
import co.edu.unicesi.sami.entidades.Curso;
import co.edu.unicesi.sami.entidades.Material;
import co.edu.unicesi.sami.entidades.MetaTerminal;
import co.edu.unicesi.sami.entidades.ObjetivoEspecifico;
import co.edu.unicesi.sami.entidades.ObjetivoTerminal;
import co.edu.unicesi.sami.entidades.Recurso;
import co.edu.unicesi.sami.entidades.RecursoAsignado;
import co.edu.unicesi.sami.entidades.Saber;
import co.edu.unicesi.sami.entidades.Sesion;
import co.edu.unicesi.sami.entidades.TrabajoAsignado;
import co.edu.unicesi.sami.entidades.Unidad;

/**
 * Session Bean implementation class GestionListadosBean
 */
@Stateless
public class GestionListadosBean implements GestionListadosRemote, GestionListadosLocal {

	@PersistenceContext
    EntityManager em;
    
    @Override
    public List<MaterialBO> listarMaterialesPorCurso( int idCurso )
    {
        Curso curso = em.find( Curso.class, idCurso );
        em.refresh( curso );
        List<MaterialBO> materiales = new ArrayList<MaterialBO>( );
        
        for(Material m : curso.getMateriales( ))
        {
            MaterialBO bo = new MaterialBO( );
            
            bo.setId( m.getId( ) );
            bo.setNombre( m.getNombre( ) );
           
            materiales.add( bo );
        }
        
        return materiales;
    }

    @Override
    public List<MetaTerminalBO> listarMetasTerminalesPorCurso( int idCurso )
    {
        String query = "SELECT DISTINCT (mt) FROM Curso c, Unidad u, MetaTerminal mt WHERE mt.unidade = u and u.curso.id = :idCurso";
        Query q = em.createQuery( query );
        q.setParameter( "idCurso", idCurso );
        List<MetaTerminal> metasTerminales = q.getResultList( );
        List<MetaTerminalBO> metasTerminalesBO = new ArrayList<MetaTerminalBO>( );
        
        for(MetaTerminal mt : metasTerminales)
        {
            MetaTerminalBO bo = new MetaTerminalBO( );
            bo.setId( mt.getId( ) );
            
            bo.setIdUnidad( mt.getUnidade( ).getId( ) );
            bo.setNumeroUnidad( mt.getUnidade( ).getNumero( ) );
            bo.setNombreUnidad( mt.getUnidade( ).getNombre( ) );
            bo.setContenidoUnidad( mt.getUnidade( ).getContenido( ) );
            
            bo.setIdObjTerminal( mt.getObjetivosTerminale( ).getId( ) );
            bo.setNombreObjTerminal( mt.getObjetivosTerminale( ).getNombre( ) );
            bo.setContenidoObjTerminal( mt.getObjetivosTerminale( ).getContenido( ) );
            
            metasTerminalesBO.add( bo );
        }
        
        return metasTerminalesBO;
    }

    @Override
    public List<ObjetivoEspecificoBO> listarObjEspecificosPorCurso( int idCurso )
    {
        String query = "SELECT DISTINCT (oe) FROM Curso c, Unidad u, MetaTerminal mt, ObjetivoEspecifico oe WHERE oe.metasTerminale = mt AND mt.unidade = u AND u.curso.id = :idCurso";
        Query q = em.createQuery( query );
        q.setParameter( "idCurso", idCurso );
        List<ObjetivoEspecifico> objEspecificos = q.getResultList( );
        List<ObjetivoEspecificoBO> objEspecificosBO = new ArrayList<ObjetivoEspecificoBO>( );
        
        for(ObjetivoEspecifico oe : objEspecificos)
        {
            ObjetivoEspecificoBO bo = new ObjetivoEspecificoBO( );
            
            bo.setId( oe.getId( ) );
            bo.setNombre( oe.getNombre( ) );
            bo.setContenido( oe.getContenido( ) );
            
            objEspecificosBO.add(bo);
        }
        
        return objEspecificosBO;
    }

    @Override
    public List<ObjetivoEspecificoBO> listarObjEspecificosPorMetaTerminal( int idMetaTerminal )
    {
        MetaTerminal metaTerminal = em.find( MetaTerminal.class, idMetaTerminal );
        em.refresh( metaTerminal );
        List<ObjetivoEspecificoBO> objEspecificos = new ArrayList<ObjetivoEspecificoBO>( );
        
        for(ObjetivoEspecifico oe : metaTerminal.getObjetivosEspecificos( ))
        {
            ObjetivoEspecificoBO bo = new ObjetivoEspecificoBO( );
            
            bo.setId( oe.getId( ) );
            bo.setNombre( oe.getNombre( ) );
            bo.setContenido( oe.getContenido( ) );
            
            objEspecificos.add( bo  );
        }
        
        return objEspecificos;
    }

    @Override
    public List<ObjetivoEspecificoBO> listarObjEspecificosPorUnidad( int idUnidad )
    {
        String query = "SELECT DISTINCT(oe) FROM Unidad u, MetaTerminal mt, ObjetivoEspecifico oe WHERE oe.metasTerminale = mt AND mt.unidade = u AND u.id = :idUnidad";
        Query q = em.createQuery( query );
        q.setParameter( "idUnidad", idUnidad );
        List<ObjetivoEspecifico> objEspecificos = q.getResultList( );
        List<ObjetivoEspecificoBO> objEspecificosBO = new ArrayList<ObjetivoEspecificoBO>( );
        
        for(ObjetivoEspecifico oe : objEspecificos)
        {
            ObjetivoEspecificoBO bo = new ObjetivoEspecificoBO( );
            
            bo.setId( oe.getId( ) );
            bo.setNombre( oe.getNombre( ) );
            bo.setContenido( oe.getContenido( ) );
            
            objEspecificosBO.add(bo);
        }
        
        return objEspecificosBO;
    }

    @Override
    public List<ObjetivoTerminalBO> listarObjTerminalesPorCurso( int idCurso )
    {
        Curso curso = em.find( Curso.class, idCurso );
        em.refresh( curso );
        List<ObjetivoTerminalBO> objTerminales = new ArrayList<ObjetivoTerminalBO>( );
        
        for(ObjetivoTerminal ot : curso.getObjetivosTerminales( ))
        {
            ObjetivoTerminalBO bo = new ObjetivoTerminalBO( );
            
            bo.setId( ot.getId( ) );
            bo.setNombre( ot.getNombre( ) );
            bo.setContenido( ot.getContenido( ) );
            
            objTerminales.add( bo  );
        }
        
        return objTerminales;
    }

    @Override
    public List<RecursoBO> listarRecursosPorSaber( int idSaber )
    {
        Saber saber = em.find( Saber.class, idSaber );
        em.refresh( saber );
        List<RecursoBO> recursos = new ArrayList<RecursoBO>( );
        
        for(Recurso r : saber.getRecursos( ))
        {
            RecursoBO bo = new RecursoBO( );
            
            bo.setId( r.getId( ) );
            
            bo.setIdSaber( r.getSabere( ).getId( ) );
            bo.setNombreSaber( r.getSabere( ).getNombre( ) );
            bo.setContenidoSaber( r.getSabere( ).getContenido( ) );
            
            bo.setIdMaterial( r.getMateriale( ).getId( ) );
            bo.setNombreMaterial( r.getMateriale( ).getNombre( ) );
            
            recursos.add( bo  );
        }
        
        return recursos;
    }
    
    @Override
    public List<SaberBO> listarSaberesPorCurso(int idCurso)
    {
        String query = "SELECT DISTINCT(s) FROM Curso c, Unidad u, MetaTerminal mt, ObjetivoEspecifico oe, Saber s WHERE s.objetivosEspecifico = oe AND oe.metasTerminale = mt AND mt.unidade = u AND u.curso.id = :idCurso";
        Query q = em.createQuery( query );
        q.setParameter( "idCurso", idCurso );
        List<Saber> saberes = q.getResultList( );
        List<SaberBO> saberesBO = new ArrayList<SaberBO>( );
        
        for(Saber s : saberes)
        {
            SaberBO bo = new SaberBO( );
            
            bo.setId( s.getId( ) );
            bo.setNombre( s.getNombre( ) );
            bo.setContenido( s.getContenido( ) );
            bo.setTipo( s.getTipo( ) );
            
            saberesBO.add(bo);
        }
        
        return saberesBO;
    }
    
    @Override
    public List<SaberBO> listarSaberesPorObjetivoEspecifico( int idObjEspecifico )
    {
        ObjetivoEspecifico objEspecifico = em.find( ObjetivoEspecifico.class, idObjEspecifico );
        em.refresh( objEspecifico );
        List<SaberBO> saberes = new ArrayList<SaberBO>( );
        
        for(Saber s : objEspecifico.getSaberes( ))
        {
            SaberBO bo = new SaberBO( );
            
            bo.setId( s.getId( ) );
            bo.setNombre( s.getNombre( ) );
            bo.setContenido( s.getContenido( ) );
            bo.setTipo( s.getTipo( ) );
            
            saberes.add( bo );
        }
        
        return saberes;
    }

    @Override
    public List<SesionBO> listarSesionesPorUnidad( int idUnidad )
    {
        Unidad unidad = em.find( Unidad.class, idUnidad );
        em.refresh( unidad );
        List<SesionBO> sesiones = new ArrayList<SesionBO>( );
        
        for(Sesion s : unidad.getSesiones( ))
        {
            SesionBO bo = new SesionBO( );
            
            bo.setId( s.getId( ) );
            bo.setNombre( s.getNombre( ) );
            bo.setNumero( s.getNumero( ) );
            
            sesiones.add( bo );
        }
        
        return sesiones;
    }

    @Override
    public List<TrabajoAsignadoBO> listarTrabajosAsignadosPorSesion( int idSesion )
    {
       Sesion sesion = em.find( Sesion.class, idSesion);
       em.refresh( sesion );
        List<TrabajoAsignadoBO> trabajosAsignados = new ArrayList<TrabajoAsignadoBO>( );
        
        for(TrabajoAsignado ta : sesion.getTrabajosAsignados( ))
        {
           TrabajoAsignadoBO bo = new TrabajoAsignadoBO( );
            
            bo.setId( ta.getId( ) );           
            bo.setEncargado( ta.getEncargado( ) );
            bo.setContenido( ta.getContenido( ) );        
            bo.setTipo( ta.getTipo( ) );
            
            trabajosAsignados.add( bo );
        }
        
        return trabajosAsignados;
    }

    @Override
    public List<RecursoAsignadoBO> listarRecursosAsignadosPorTrabajoAsignado( int idTrabajoAsignado )
    {
        TrabajoAsignado trabajoAsignado = em.find( TrabajoAsignado.class, idTrabajoAsignado );
        em.refresh( trabajoAsignado );
        List<RecursoAsignadoBO> recursos = new ArrayList<RecursoAsignadoBO>( );
        
        for(RecursoAsignado r : trabajoAsignado.getRecursosAsignados( ))
        {
            RecursoAsignadoBO bo = new RecursoAsignadoBO( );
            bo.setId( r.getId( ) );
            
            bo.setIdRecurso( r.getRecurso( ).getId( ) );
            
            bo.setIdMaterial( r.getRecurso( ).getMateriale( ).getId( ) );
            bo.setNombreMaterial( r.getRecurso( ).getMateriale( ).getNombre( ) );
            
            bo.setIdSaber( r.getRecurso( ).getSabere( ).getId( ) );
            bo.setNombreSaber( r.getRecurso( ).getSabere( ).getNombre( ) );
            bo.setContenidoSaber( r.getRecurso( ).getSabere( ).getContenido( ) );
            bo.setTipoSaber( r.getRecurso( ).getSabere( ).getTipo( ) );
            
            recursos.add( bo );
        }
        return recursos;
    }

    @Override
    public List<UnidadBO> listarUnidadesPorCurso( int idCurso )
    {
        Curso curso = em.find( Curso.class, idCurso );
        em.refresh( curso );
        List<UnidadBO> unidades = new ArrayList<UnidadBO>( );
        
        for( Unidad u : curso.getUnidades( ) )
        {
            UnidadBO bo = new UnidadBO( );
            
            bo.setId( u.getId( ) );
            bo.setNumero( u.getNumero( ) );
            bo.setNombre( u.getNombre( ) );
            bo.setContenido( u.getContenido( ) );
            
            unidades.add( bo );
        }
        
        return unidades;
    }

}
