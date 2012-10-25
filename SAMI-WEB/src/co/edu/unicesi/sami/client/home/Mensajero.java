package co.edu.unicesi.sami.client.home;

import com.google.gwt.core.client.GWT;

import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;

public class Mensajero
{
    private final static MultiLingualConstants MultiLingualConstants = GWT.create( MultiLingualConstants.class );
    public final static String ON_FAILURE = MultiLingualConstants.onFailure( );
    public static final int AGREGAR = 0;
    public static final int EDITAR = 1;
    public static final int ELIMINAR = 2;
    public static final int ERROR = -1;
    
    private Mensajero()
    {
        
    }
    
    public static String mostrarMensaje(int tipo)
    {
        String mensaje = MultiLingualConstants.msgError( );
        
        switch( tipo )
        {
            case AGREGAR:
                mensaje = MultiLingualConstants.msgAgregar( );
                break;
            case EDITAR:
                mensaje = MultiLingualConstants.msgEditar( );
                break;
            case ELIMINAR:
                mensaje = MultiLingualConstants.msgEliminar( );
                break;            
        }
        
        return mensaje;
    }
}
