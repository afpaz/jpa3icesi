package co.edu.unicesi.sami.bo;

import java.io.Serializable;
import java.util.List;

public class TrabajoAsignadoBO implements Serializable
{
    private int id;
    private String contenido;
    private String encargado;
    private String tipo;
    private int idSesion;
    private int numeroSesion;
    private String nombreSesion;
    private List<Integer> recursosAsignados;
    
	private static final long serialVersionUID = 1L;
    
    public TrabajoAsignadoBO( )
    {
        
    }

    public int getId( )
    {
        return id;
    }

    public String getContenido( )
    {
        return contenido;
    }

    public String getEncargado( )
    {
        return encargado;
    }

    public String getTipo( )
    {
        return tipo;
    }

    public int getIdSesion( )
    {
        return idSesion;
    }

    public int getNumeroSesion( )
    {
        return numeroSesion;
    }

    public String getNombreSesion( )
    {
        return nombreSesion;
    }

    public List<Integer> getRecursosAsignados( )
    {
        return recursosAsignados;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public void setContenido( String contenido )
    {
        this.contenido = contenido;
    }

    public void setEncargado( String encargado )
    {
        this.encargado = encargado;
    }

    public void setTipo( String tipo )
    {
        this.tipo = tipo;
    }

    public void setIdSesion( int idSesion )
    {
        this.idSesion = idSesion;
    }

    public void setNumeroSesion( int numeroSesion )
    {
        this.numeroSesion = numeroSesion;
    }

    public void setNombreSesion( String nombreSesion )
    {
        this.nombreSesion = nombreSesion;
    }

    public void setRecursosAsignados( List<Integer> recursosAsignados )
    {
        this.recursosAsignados = recursosAsignados;
    }
}
