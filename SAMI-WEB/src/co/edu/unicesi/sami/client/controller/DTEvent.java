package co.edu.unicesi.sami.client.controller;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;

public class DTEvent
{      
    public final static EventType ACTUALIZAR_OBJ_GENERAL = new EventType( );
    public final static EventType LISTAR_CURSOS = new EventType( );
    public static final EventType LISTAR_MATERIALES = new EventType( );   
    public final static EventType LISTAR_METAS_TERMINALES = new EventType( );
    public final static EventType LISTAR_OBJ_ESPECIFICOS = new EventType( );
    public final static EventType LISTAR_OBJ_TERMINALES = new EventType( );    
    public final static EventType LISTAR_OBJ_ESPECIFICOS_POR_META_TERMINAL = new EventType( );
    public static final EventType LISTAR_RECURSOS_POR_SABER = new EventType( );
    public static final EventType LISTAR_SABERES = new EventType( );
    public static final EventType LISTAR_SABERES_POR_OBJ_ESPECIFICO = new EventType( );
    public final static EventType SELECCIONAR_CURSO = new EventType( );
	public static final EventType LISTAR_UNIDADES = new EventType();
	public static final EventType LISTAR_OBJ_TERMINALES_UNIDADES = new EventType();
	
    
    public static AppEvent getAppEvent(EventType ev)
    {
        return new AppEvent(ev);
    }
}
