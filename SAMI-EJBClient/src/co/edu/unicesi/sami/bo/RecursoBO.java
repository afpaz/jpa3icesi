package co.edu.unicesi.sami.bo;

import java.io.Serializable;
import java.util.List;

public class RecursoBO implements Serializable
{
    private int id;
    private int idSaber;
    private String nombreSaber;
    private String contenidoSaber;
    private String tipoSaber;
    private int idMaterial;
    private String nombreMaterial;
    private List<Integer> recursosAsignados;
    
	private static final long serialVersionUID = 1L;
    
    public RecursoBO( )
    {
        
    }

    public int getId( )
    {
        return id;
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

    public List<Integer> getRecursosAsignados( )
    {
        return recursosAsignados;
    }

    public void setId( int id )
    {
        this.id = id;
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

    public void setRecursosAsignados( List<Integer> recursosAsignados )
    {
        this.recursosAsignados = recursosAsignados;
    }        
}
