package co.edu.unicesi.sami.bo;

import java.io.Serializable;
import java.util.List;

public class MaterialBO implements Serializable
{
    private int id;
    private String nombre;
    private int idCurso;
    private String codigoCurso;
    private String nombreCurso;
    private List<Integer> recursos;    

	private static final long serialVersionUID = 1L;
    
    public MaterialBO()
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

    public void setRecursos( List<Integer> recursos )
    {
        this.recursos = recursos;
    }    
}
