package co.edu.unicesi.sami.client.home.dialogos;

import co.edu.unicesi.sami.client.home.TabObjTerminales;
import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.google.gwt.core.client.GWT;

public class DialogoAgregarObjTerminal extends Dialog
{
    private final static MultiLingualConstants MultiLingualConstants = GWT.create( MultiLingualConstants.class );

    private TabObjTerminales tabObjTerminales;
    private Text labNombre;
    private Text labContenido;
    private TextField<String> txtNombre;
    private TextArea txtContenido;

    public DialogoAgregarObjTerminal(TabObjTerminales tabObjTerminales )
    {
        this.tabObjTerminales = tabObjTerminales;
        
        setModal( true );
        setSize( 500, 300 );
        setHeading( MultiLingualConstants.dialogoAgregarObjTerminal_heading( ) );
        setLayout( new AbsoluteLayout( ) );

        txtNombre = new TextField<String>( );
        add( txtNombre, new AbsoluteData( 157, 25 ) );
        txtNombre.setSize( "212px", "24px" );

        txtContenido = new TextArea( );
        add( txtContenido, new AbsoluteData( 157, 72 ) );
        txtContenido.setSize( "212px", "100px" );

        labNombre = new Text( MultiLingualConstants.labNombre_text( ) );
        add( labNombre, new AbsoluteData( 30, 36 ) );

        labContenido = new Text( MultiLingualConstants.labContenido_text( ) );
        add( labContenido, new AbsoluteData( 30, 72 ) );

        eventoAgregarObjTerminal( );
        eventoCerrarVentana( );
    }

    private void eventoAgregarObjTerminal( )
    {
        getButtonById( OK ).addSelectionListener( new SelectionListener<ButtonEvent>( )
        {
            @Override
            public void componentSelected( ButtonEvent ce )
            {
                agregarObjTerminal( );
                limpiarDatos( );
            }
        } );
    }

    private void eventoCerrarVentana( )
    {
        this.addListener( Events.Close, new Listener<BaseEvent>( )
        {
            @Override
            public void handleEvent( BaseEvent be )
            {
                limpiarDatos( );
            }
        } );
    }

    private void agregarObjTerminal( )
    {
        String nombre = txtNombre.getValue( );
        String contenido = txtContenido.getValue( );
        
        tabObjTerminales.agregarObjTerminal( nombre, contenido );
    }

    private void limpiarDatos( )
    {
        txtNombre.clear( );
        txtContenido.clear( );
        hide( );
    }
}
