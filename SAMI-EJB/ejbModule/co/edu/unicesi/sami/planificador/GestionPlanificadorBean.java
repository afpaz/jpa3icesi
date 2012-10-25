package co.edu.unicesi.sami.planificador;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.unicesi.sami.bo.MaterialBO;
import co.edu.unicesi.sami.bo.RecursoAsignadoBO;
import co.edu.unicesi.sami.bo.RecursoBO;
import co.edu.unicesi.sami.bo.SaberBO;
import co.edu.unicesi.sami.bo.SesionBO;
import co.edu.unicesi.sami.bo.TrabajoAsignadoBO;
import co.edu.unicesi.sami.entidades.Curso;
import co.edu.unicesi.sami.entidades.Material;
import co.edu.unicesi.sami.entidades.ObjetivoEspecifico;
import co.edu.unicesi.sami.entidades.Recurso;
import co.edu.unicesi.sami.entidades.RecursoAsignado;
import co.edu.unicesi.sami.entidades.Saber;
import co.edu.unicesi.sami.entidades.Sesion;
import co.edu.unicesi.sami.entidades.TrabajoAsignado;
import co.edu.unicesi.sami.entidades.Unidad;

/**
 * Session Bean implementation class GestionPlanificadorBean
 */
@Stateless
public class GestionPlanificadorBean implements GestionPlanificadorRemote, GestionPlanificadorLocal {

	@PersistenceContext
	EntityManager em;

	@Override
	public int agregarMaterial( MaterialBO material )
	{
		Material entidad=new Material();
		int resp=-1;

		try {
			Curso curso=em.find(Curso.class, material.getIdCurso());

			entidad.setNombre(material.getNombre());
			entidad.setRecursos(new ArrayList<Recurso>());
			entidad.setCurso(curso);

			em.persist(entidad);

			resp=0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public int editarMaterial( MaterialBO material )
	{
		int resp=-1;
		try {			
			Material entidad=em.find(Material.class, material.getId());
			entidad.setNombre(material.getNombre());

			em.merge(entidad);
			resp=1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public MaterialBO buscarMaterial( int idMaterial )
	{
		Material material=em.find(Material.class, idMaterial);
        em.refresh( material );
		MaterialBO bo=new MaterialBO();

		bo.setCodigoCurso(material.getCurso().getCodigo());
		bo.setId(material.getId());
		bo.setIdCurso(material.getCurso().getId());
		bo.setNombre(material.getNombre());
		bo.setNombreCurso(material.getCurso().getNombre());

		List<Integer> recursosID=new ArrayList<Integer>();

		for (Recurso rec : material.getRecursos()) {
			recursosID.add(rec.getId());
		}

		bo.setRecursos(recursosID);

		return bo;
	}

	@Override
	public int agregarSaber( SaberBO saber )
	{
		Saber entidad=new Saber();
		int resp=-1;

		try {
			ObjetivoEspecifico objetivoEspefico=em.find(ObjetivoEspecifico.class, saber.getIdObjEspecifico());

			entidad.setContenido(saber.getContenido());
			entidad.setNombre(saber.getNombre());
			entidad.setRecursos(new ArrayList<Recurso>());
			entidad.setTipo(saber.getTipo());
			entidad.setObjetivosEspecifico(objetivoEspefico);

			em.persist(entidad);

			resp=0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public int editarSaber( SaberBO saber )
	{
		int resp=-1;
		try {			
			Saber entidad=em.find(Saber.class, saber.getId());
			entidad.setContenido(saber.getContenido());
			entidad.setNombre(saber.getNombre());
			entidad.setTipo(saber.getTipo());	

			em.merge(entidad);
			resp=1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public SaberBO buscarSaber( int idSaber )
	{
		Saber saber=em.find(Saber.class, idSaber);
        em.refresh( saber );

		SaberBO bo=new SaberBO();

		bo.setContenido(saber.getContenido());
		bo.setContenidoObjEspecifico(saber.getObjetivosEspecifico().getContenido());
		bo.setIdObjEspecifico(saber.getObjetivosEspecifico().getId());
		bo.setNombre(saber.getNombre());
		bo.setNombreObjEspecifico(saber.getObjetivosEspecifico().getNombre());		
		bo.setTipo(saber.getTipo());

		List<Integer> recursos=new ArrayList<Integer>();

		for (Recurso rec : saber.getRecursos()) {
			recursos.add(rec.getId());
		}	

		bo.setRecursos(recursos);

		return bo;
	}

	@Override
	public int agregarRecurso( RecursoBO recurso )
	{
		Recurso entidad=new Recurso();
		int resp=-1;

		try {
			Material material=em.find(Material.class, recurso.getIdMaterial());
			Saber saber=em.find(Saber.class, recurso.getIdSaber());

			entidad.setMateriale(material);
			entidad.setSabere(saber);
			entidad.setRecursosAsignados(new ArrayList<RecursoAsignado>());

			em.persist(entidad);

			resp=0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public int eliminarRecurso( RecursoBO recurso )
	{
		int resp=-1;
		try {			
			Recurso entidad=em.find(Recurso.class, recurso.getId());

			em.remove(entidad);
			resp=1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public RecursoBO buscarRecurso( int idRecurso )
	{
		Recurso recurso=em.find(Recurso.class, idRecurso);
        em.refresh( recurso );
		RecursoBO bo=new RecursoBO();

		bo.setContenidoSaber(recurso.getSabere().getContenido());
		bo.setId(recurso.getId());
		bo.setIdMaterial(recurso.getMateriale().getId());
		bo.setIdSaber(recurso.getSabere().getId());
		bo.setNombreMaterial(recurso.getMateriale().getNombre());
		bo.setNombreSaber(recurso.getSabere().getNombre());
		bo.setTipoSaber(recurso.getSabere().getTipo());

		List<Integer> recursosAsignados=new ArrayList<Integer>();

		for (RecursoAsignado trab : recurso.getRecursosAsignados()) {
			recursosAsignados.add(trab.getId());
		}	

		bo.setRecursosAsignados(recursosAsignados);

		return bo;
	}

	@Override
	public int agregarSesion( SesionBO sesion )
	{
		Sesion entidad=new Sesion();
		int resp=-1;

		try {
			Unidad unidad=em.find(Unidad.class, sesion.getIdUnidad());

			entidad.setNombre(sesion.getNombre());
			entidad.setNumero(sesion.getNumero());
			entidad.setTrabajosAsignados(new ArrayList<TrabajoAsignado>());
			entidad.setUnidade(unidad);

			em.persist(entidad);

			resp=0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public int editarSesion( SesionBO sesion )
	{
		int resp=-1;
		try {			
			Sesion entidad=em.find(Sesion.class, sesion.getId());
			entidad.setNombre(sesion.getNombre());
			entidad.setNumero(sesion.getNumero());

			em.merge(entidad);
			resp=1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public SesionBO buscarSesion( int idSesion )
	{
		Sesion sesion=em.find(Sesion.class, idSesion);
		em.refresh( sesion );
		SesionBO bo=new SesionBO();

		bo.setContenidoUnidad(sesion.getUnidade().getContenido());
		bo.setId(sesion.getId());
		bo.setIdUnidad(sesion.getUnidade().getId());
		bo.setNombre(sesion.getUnidade().getNombre());
		bo.setNombreUnidad(sesion.getUnidade().getNombre());
		bo.setNumero(sesion.getUnidade().getNumero());
		bo.setNumeroUnidad(sesion.getUnidade().getNumero());

		List<Integer> trabajosAsignados=new ArrayList<Integer>();

		for (TrabajoAsignado trab : sesion.getTrabajosAsignados()) {
			trabajosAsignados.add(trab.getId());
		}	

		bo.setTrabajosAsignados(trabajosAsignados);

		return bo;
	}

	@Override
	public int agregarTrabajoAsignado( TrabajoAsignadoBO trabajoAsignado )
	{
		TrabajoAsignado entidad=new TrabajoAsignado();
		int resp=-1;

		try {
			Sesion sesion=em.find(Sesion.class, trabajoAsignado.getIdSesion());

			entidad.setContenido(trabajoAsignado.getContenido());
			entidad.setEncargado(trabajoAsignado.getEncargado());
			entidad.setRecursosAsignados(new ArrayList<RecursoAsignado>());
			entidad.setTipo(trabajoAsignado.getTipo());
			entidad.setSesione(sesion);

			em.persist(entidad);

			resp=0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public int editarTrabajoAsignado( TrabajoAsignadoBO trabajoAsignado )
	{
		int resp=-1;
		try {			
			TrabajoAsignado entidad=em.find(TrabajoAsignado.class, trabajoAsignado.getId());
			entidad.setContenido(trabajoAsignado.getContenido());
			entidad.setEncargado(trabajoAsignado.getEncargado());
			entidad.setTipo(trabajoAsignado.getTipo());

			em.merge(entidad);
			resp=1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public TrabajoAsignadoBO buscarTrabajoAsignado( int idTrabajoAsignado )
	{
		TrabajoAsignado trabajoAsignado=em.find(TrabajoAsignado.class, idTrabajoAsignado);
		em.refresh( trabajoAsignado );
		TrabajoAsignadoBO bo=new TrabajoAsignadoBO();

		bo.setContenido(trabajoAsignado.getContenido());
		bo.setEncargado(trabajoAsignado.getEncargado());
		bo.setId(trabajoAsignado.getId());
		bo.setIdSesion(trabajoAsignado.getSesione().getId());
		bo.setNombreSesion(trabajoAsignado.getSesione().getNombre());
		bo.setNumeroSesion(trabajoAsignado.getSesione().getNumero());
		bo.setTipo(trabajoAsignado.getTipo());

		List<Integer> recursosAsignados=new ArrayList<Integer>();

		for (RecursoAsignado trab : trabajoAsignado.getRecursosAsignados()) {
			recursosAsignados.add(trab.getId());
		}	

		bo.setRecursosAsignados(recursosAsignados);

		return bo;
	}

	@Override
	public int agregarRecursoAsignado( RecursoAsignadoBO recursoAsignado )
	{
		RecursoAsignado entidad=new RecursoAsignado();
		int resp=-1;

		try {
			Recurso recurso=em.find(Recurso.class, recursoAsignado.getIdMaterial());
			TrabajoAsignado trabajoAsignado=em.find(TrabajoAsignado.class, recursoAsignado.getIdTrabajoAsignado());

			entidad.setRecurso(recurso);
			entidad.setTrabajosAsignado(trabajoAsignado);

			em.persist(entidad);

			resp=0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public int eliminarRecursoAsignado( RecursoAsignadoBO recursoAsignado )
	{
		int resp=-1;
		try {			
			RecursoAsignado entidad=em.find(RecursoAsignado.class,recursoAsignado.getIdTrabajoAsignado());

			em.remove(entidad);
			resp=1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
		return resp;
	}

	@Override
	public RecursoAsignadoBO buscarRecursoAsignado( int idRecursoAsignado )
	{
		RecursoAsignado recursoAsignado=em.find(RecursoAsignado.class, idRecursoAsignado);
		em.refresh( recursoAsignado );
		RecursoAsignadoBO bo=new RecursoAsignadoBO();

		bo.setContenidoTrabajoAsignado(recursoAsignado.getTrabajosAsignado().getContenido());
		bo.setEncargadoTrabajoAsignado(recursoAsignado.getTrabajosAsignado().getEncargado());
		bo.setId(recursoAsignado.getId());
		bo.setIdMaterial(recursoAsignado.getRecurso().getMateriale().getId());
		bo.setIdRecurso(recursoAsignado.getRecurso().getId());
		bo.setIdSaber(recursoAsignado.getRecurso().getSabere().getId());
		bo.setIdTrabajoAsignado(idRecursoAsignado);
		bo.setTipoTrabajoAsignado(recursoAsignado.getTrabajosAsignado().getTipo());

		return bo;
	}
}
