package co.edu.unicesi.sami.client.model;

import java.io.Serializable;

import co.edu.unicesi.sami.bo.MetaTerminalBO;

import com.extjs.gxt.ui.client.data.BaseModel;

public class MetaTerminalModel extends BaseModel implements Serializable
{  
    public MetaTerminalModel()
    {
        
    }
    
    public MetaTerminalModel(int id, String nombreUnidad, String contenidoUnidad, String nombreObjTerminal, String contenidoObjTerminal)
    {
        get("id",id);
        get("nombreUnidad", nombreUnidad);
        get("contenidoUnidad", contenidoUnidad);
        get("nombreObjTerminal", nombreObjTerminal);
        get("contenidoObjTerminal", contenidoObjTerminal);
    }

    public int getId( )
    {
        return (Integer) get("id");
    }
    
    public String getNombreUnidad( )
    {
        return (String) get("nombreUnidad");
    }

    public String getContenidoUnidad( )
    {
        return (String) get("contenidoUnidad");
    }
    
    public String getNombreObjTerminal( )
    {
        return (String) get("nombreObjTerminal");
    }

    public String getContenidoObjTerminal( )
    {
        return (String) get("contenidoObjTerminal");
    }

    public void setId( int id )
    {
        set("id", id);
    }

    public void setNombreUnidad( String nombreUnidad )
    {
        set("nombreUnidad", nombreUnidad);
    }

    public void setContenidoUnidad( String contenidoUnidad )
    {
        set("contenidoUnidad", contenidoUnidad);
    }

    public void setNombreObjTerminal( String nombreObjTerminal )
    {
        set("nombreObjTerminal", nombreObjTerminal);
    }

    public void setContenidoObjTerminal( String contenidoObjTerminal )
    {
        set("contenidoObjTerminal", contenidoObjTerminal);
    }         
    
    public static MetaTerminalModel toModelFromBO(MetaTerminalBO bo)
    {
        MetaTerminalModel mModel = new MetaTerminalModel( );
        mModel.setId( bo.getId( ) );
        mModel.setNombreUnidad( bo.getNombreUnidad( ) );
        mModel.setContenidoUnidad( bo.getContenidoUnidad( ) );
        mModel.setNombreObjTerminal( bo.getNombreObjTerminal( ) );
        mModel.setContenidoObjTerminal( bo.getContenidoObjTerminal( ) );
        
        return mModel;
    }
}
