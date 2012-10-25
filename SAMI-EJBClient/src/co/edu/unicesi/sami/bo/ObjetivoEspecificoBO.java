package co.edu.unicesi.sami.bo;

import java.io.Serializable;
import java.util.List;

public class ObjetivoEspecificoBO implements Serializable
{
    private int id;
    private String nombre;
    private String contenido;
    private int idMetaTerminal;
    private int idObjTerminal;
    private int idUnidad;
    private List<Integer> saberes;
    
	private static final long serialVersionUID = 1L;
    
    public ObjetivoEspecificoBO()
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

    public int getIdMetaTerminal( )
    {
        return idMetaTerminal;
    }

    public int getIdObjTerminal( )
    {
        return idObjTerminal;
    }

    public int getIdUnidad( )
    {
        return idUnidad;
    }

    public List<Integer> getSaberes( )
    {
        return saberes;
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

    public void setIdMetaTerminal( int idMetaTerminal )
    {
        this.idMetaTerminal = idMetaTerminal;
    }

    public void setIdObjTerminal( int idObjTerminal )
    {
        this.idObjTerminal = idObjTerminal;
    }

    public void setIdUnidad( int idUnidad )
    {
        this.idUnidad = idUnidad;
    }

    public void setSaberes( List<Integer> saberes )
    {
        this.saberes = saberes;
    }    
}
