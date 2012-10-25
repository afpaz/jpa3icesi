package co.edu.unicesi.sami.client.model;

import java.io.Serializable;
import java.util.List;

import co.edu.unicesi.sami.bo.ObjetivoEspecificoBO;
import co.edu.unicesi.sami.bo.ObjetivoTerminalBO;

import com.extjs.gxt.ui.client.data.BaseModel;

public class ObjetivoTerminalModel extends BaseModel implements Serializable
{   
    public ObjetivoTerminalModel( )
    {
        
    }
    
    public ObjetivoTerminalModel(int id, String nombre, String contenido)
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
    
    public static ObjetivoTerminalModel toModelFromBO(ObjetivoTerminalBO bo)
    {
        ObjetivoTerminalModel otModel = new ObjetivoTerminalModel( );
        otModel.setId( bo.getId( ) );
        otModel.setNombre( bo.getNombre() );
        otModel.setContenido( bo.getContenido() );
           
        return otModel;
    }
}
