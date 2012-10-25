package co.edu.unicesi.sami.client.model;

import java.io.Serializable;
import java.util.List;

import co.edu.unicesi.sami.bo.CursoBO;

import com.extjs.gxt.ui.client.data.BaseModel;

public class CursoModel extends BaseModel implements Serializable
{
    
    public CursoModel()
    {
        
    }
    
    public CursoModel(int id, String nombre, String codigo)
    {
        set( "id", id );
        set("nombre", nombre);
        set("codigo", codigo);
    }

    public int getId( )
    {
        return (Integer) get("id");
    }

    public String getCodigo( )
    {
        return (String) get("codigo");
    }

    public String getNombre( )
    {
        return (String) get( "nombre" );
    }    

    public void setId( int id )
    {
        set("id", id);
    }

    public void setCodigo( String codigo )
    {
        set("codigo", codigo);
    }

    public void setNombre( String nombre )
    {
        set( "nombre", nombre );
    }       
    
    public static CursoModel toModelFromBO(CursoBO bo)
    {
        CursoModel cModel = new CursoModel( );
        cModel.setId( bo.getId( ));
        cModel.setCodigo( bo.getCodigo( ) );
        cModel.setNombre( bo.getNombre( ) );
        
        return cModel;
    }
}
