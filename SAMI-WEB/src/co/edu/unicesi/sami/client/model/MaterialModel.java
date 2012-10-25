package co.edu.unicesi.sami.client.model;

import java.io.Serializable;
import java.util.List;

import co.edu.unicesi.sami.bo.MaterialBO;
import co.edu.unicesi.sami.bo.RecursoBO;
import co.edu.unicesi.sami.bo.SaberBO;

import com.extjs.gxt.ui.client.data.BaseModel;

public class MaterialModel extends BaseModel implements Serializable
{   
    public MaterialModel()
    {
        
    }
    
    public MaterialModel(int id, String nombre)
    {
        set("id", id);
        set("nombre", nombre);
    }

    public int getId( )
    {
        return (Integer) get("id");
    }

    public String getNombre( )
    {
        return (String) get("nombre");
    }

    public void setId( int id )
    {
        set("id", id);
    }

    public void setNombre( String nombre )
    {
       set("nombre", nombre);
    }

    public static MaterialModel toModelFromBO( MaterialBO bo )
    {
        MaterialModel mModel = new MaterialModel( );
        mModel.set( "id", bo.getId( ) );
        mModel.set( "nombre", bo.getNombre( ) );
        
        return mModel;
    }   
    
    public static MaterialModel toModelFromBO( RecursoBO bo )
    {
        MaterialModel mModel = new MaterialModel( );
        mModel.set( "id", bo.getId( ) );
        mModel.set( "nombre", bo.getNombreMaterial( ) );
        
        return mModel;
    } 
}
