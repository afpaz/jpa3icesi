package co.edu.unicesi.sami.bo;

import java.io.Serializable;

public class RecursoAsignadoBO implements Serializable
{
    private int id;
    private int idTrabajoAsignado;
    private String contenidoTrabajoAsignado;
    private String encargadoTrabajoAsignado;
    private String tipoTrabajoAsignado;
    private int idRecurso;
    private int idSaber;
    private String nombreSaber;
    private String contenidoSaber;
    private String tipoSaber;
    private int idMaterial;
    private String nombreMaterial;
    
	private static final long serialVersionUID = 1L;
    
    public RecursoAsignadoBO( )
    {
        
    }

    public int getId( )
    {
        return id;
    }

    public int getIdTrabajoAsignado( )
    {
        return idTrabajoAsignado;
    }

    public String getContenidoTrabajoAsignado( )
    {
        return contenidoTrabajoAsignado;
    }

    public String getEncargadoTrabajoAsignado( )
    {
        return encargadoTrabajoAsignado;
    }

    public String getTipoTrabajoAsignado( )
    {
        return tipoTrabajoAsignado;
    }

    public int getIdRecurso( )
    {
        return idRecurso;
    }

    public int getIdSaber( )
    {
        return idSaber;
    }

    public String getNombreSaber( )
    {
        return nombreSaber;
    }

    public String getContenidoSaber( )
    {
        return contenidoSaber;
    }

    public String getTipoSaber( )
    {
        return tipoSaber;
    }

    public int getIdMaterial( )
    {
        return idMaterial;
    }

    public String getNombreMaterial( )
    {
        return nombreMaterial;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public void setIdTrabajoAsignado( int idTrabajoAsignado )
    {
        this.idTrabajoAsignado = idTrabajoAsignado;
    }

    public void setContenidoTrabajoAsignado( String contenidoTrabajoAsignado )
    {
        this.contenidoTrabajoAsignado = contenidoTrabajoAsignado;
    }

    public void setEncargadoTrabajoAsignado( String encargadoTrabajoAsignado )
    {
        this.encargadoTrabajoAsignado = encargadoTrabajoAsignado;
    }

    public void setTipoTrabajoAsignado( String tipoTrabajoAsignado )
    {
        this.tipoTrabajoAsignado = tipoTrabajoAsignado;
    }

    public void setIdRecurso( int idRecurso )
    {
        this.idRecurso = idRecurso;
    }

    public void setIdSaber( int idSaber )
    {
        this.idSaber = idSaber;
    }

    public void setNombreSaber( String nombreSaber )
    {
        this.nombreSaber = nombreSaber;
    }

    public void setContenidoSaber( String contenidoSaber )
    {
        this.contenidoSaber = contenidoSaber;
    }

    public void setTipoSaber( String tipoSaber )
    {
        this.tipoSaber = tipoSaber;
    }

    public void setIdMaterial( int idMaterial )
    {
        this.idMaterial = idMaterial;
    }

    public void setNombreMaterial( String nombreMaterial )
    {
        this.nombreMaterial = nombreMaterial;
    }
}
