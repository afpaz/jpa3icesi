package co.edu.unicesi.sami.bo;

import java.io.Serializable;
import java.util.List;

public class ObjetivoTerminalBO implements Serializable
{
    private int id;
    private String nombre;
    private String contenido;
    private int idCurso;
    private String codigoCurso;
    private String nombreCurso;
    private List<Integer> metasTerminales;
    
	private static final long serialVersionUID = 1L;
    
    public ObjetivoTerminalBO( )
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

    public int getIdCurso( )
    {
        return idCurso;
    }

    public String getCodigoCurso( )
    {
        return codigoCurso;
    }

    public String getNombreCurso( )
    {
        return nombreCurso;
    }

    public List<Integer> getMetasTerminales( )
    {
        return metasTerminales;
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

    public void setIdCurso( int idCurso )
    {
        this.idCurso = idCurso;
    }

    public void setCodigoCurso( String codigoCurso )
    {
        this.codigoCurso = codigoCurso;
    }

    public void setNombreCurso( String nombreCurso )
    {
        this.nombreCurso = nombreCurso;
    }

    public void setMetasTerminales( List<Integer> metasTerminales )
    {
        this.metasTerminales = metasTerminales;
    }
}
