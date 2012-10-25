package co.edu.unicesi.sami.client.model;

import java.io.Serializable;
import java.util.List;

import co.edu.unicesi.sami.bo.MetaTerminalBO;
import co.edu.unicesi.sami.bo.ObjetivoEspecificoBO;

import com.extjs.gxt.ui.client.data.BaseModel;

public class ObjetivoEspecificoModel extends BaseModel implements Serializable
{
    public ObjetivoEspecificoModel( )
    {
        
    }
    
    public ObjetivoEspecificoModel(int id, String nombre, String contenido)
    {
        set("id", id);
        set("nombre", nombre);
        set("contenido", contenido);
    }

    public int getId( )
    {
        return (Integer) get("id");
    }

    public String getNombre( )
    {
        return (String) get("nombre");
    }

    public String getContenido( )
    {
        return (String) get("contenido");
    }
   
    public void setId( int id )
    {
        set("id", id);
    }

    public void setNombre( String nombre )
    {
        set("nombre", nombre);
    }

    public void setContenido( String contenido )
    {
        set("contenido", contenido);
    }
    
    public static ObjetivoEspecificoModel toModelFromBO(ObjetivoEspecificoBO bo)
    {
        ObjetivoEspecificoModel oeModel = new ObjetivoEspecificoModel( );
        oeModel.setId( bo.getId( ) );
        oeModel.setNombre( bo.getNombre() );
        oeModel.setContenido( bo.getContenido() );
           
        return oeModel;
    }
}
