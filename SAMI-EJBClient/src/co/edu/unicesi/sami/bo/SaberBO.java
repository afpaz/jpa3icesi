package co.edu.unicesi.sami.bo;

import java.io.Serializable;
import java.util.List;

public class SaberBO implements Serializable
{
    private int id;
    private String nombre;
    private String contenido;
    private String tipo;
    private int idObjEspecifico;
    private String nombreObjEspecifico;
    private String contenidoObjEspecifico;    
    private List<Integer> recursos;
    
	private static final long serialVersionUID = 1L;
    
    public SaberBO( )
    {
        
    }

    public int getId( )
    {
        return id;
    }

    public String getNombre( )
    {
        return nombre;
    }

    public String getContenido( )
    {
        return contenido;
    }

    public String getTipo( )
    {
        return tipo;
    }

    public int getIdObjEspecifico( )
    {
        return idObjEspecifico;
    }

    public String getNombreObjEspecifico( )
    {
        return nombreObjEspecifico;
    }

    public String getContenidoObjEspecifico( )
    {
        return contenidoObjEspecifico;
    }

    public List<Integer> getRecursos( )
    {
        return recursos;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public void setNombre( String nombre )
    {
        this.nombre = nombre;
    }

    public void setContenido( String contenido )
    {
        this.contenido = contenido;
    }

    public void setTipo( String tipo )
    {
        this.tipo = tipo;
    }

    public void setIdObjEspecifico( int idObjEspecifico )
    {
        this.idObjEspecifico = idObjEspecifico;
    }

    public void setNombreObjEspecifico( String nombreObjEspecifico )
    {
        this.nombreObjEspecifico = nombreObjEspecifico;
    }

    public void setContenidoObjEspecifico( String contenidoObjEspecifico )
    {
        this.contenidoObjEspecifico = contenidoObjEspecifico;
    }

    public void setRecursos( List<Integer> recursos )
    {
        this.recursos = recursos;
    }        
}
