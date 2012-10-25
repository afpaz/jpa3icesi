package co.edu.unicesi.sami.client.home.dialogos;

import co.edu.unicesi.sami.client.home.TabSaberes;
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

public class DialogoAgregarSaber extends Dialog
{
    private final static MultiLingualConstants MultiLingualConstants = GWT.create( MultiLingualConstants.class );

    private TabSaberes tabSaberes;

    private Text labNombre;
    private Text labContenido;
    private Text labTipo;

    private TextField<String> txtNombre;
    private TextArea txtContenido;
    private TextField<String> txtTipo;
    
    public DialogoAgregarSaber( TabSaberes tabSaberes )
    {
        this.tabSaberes = tabSaberes;

        setModal( true );
        setSize( 500, 300 );
        setHeading( MultiLingualConstants.dialogoAgregarSaber_heading( ) );
        setLayout( new AbsoluteLayout( ) );

        txtNombre = new TextField<String>( );
        add( txtNombre, new AbsoluteData( 157, 25 ) );
        txtNombre.setSize( "212px", "24px" );      

        txtTipo = new TextField<String>( );
        add( txtTipo, new AbsoluteData( 157, 68 ) );
        txtTipo.setSize( "212px", "24px" );        
        
        txtContenido = new TextArea( );
        add( txtContenido, new AbsoluteData( 157, 110 ) );
        txtContenido.setSize( "212px", "60px" );

        labNombre = new Text( MultiLingualConstants.labNombre_text( ) );
        add( labNombre, new AbsoluteData( 30, 36 ) );

        labTipo = new Text( MultiLingualConstants.labTipo_text( ) );
        add( labTipo, new AbsoluteData( 30, 79 ) );
        
        labContenido = new Text( MultiLingualConstants.labContenido_text( ) );
        add( labContenido, new AbsoluteData( 30, 110 ) );
        
        eventoAgregarSaber( );
        eventoCerrarVentana( );
    }
    
    private void eventoAgregarSaber( )
    {
        getButtonById( OK ).addSelectionListener( new SelectionListener<ButtonEvent>( )
        {
            @Override
            public void componentSelected( ButtonEvent ce )
            {
                agregarSaber( );
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
    
    private void agregarSaber()
    {
        String nombre = txtNombre.getValue( );
        String contenido = txtContenido.getValue( );
        String tipo = txtTipo.getValue( );
        
        tabSaberes.agregarSaber( nombre, tipo, contenido );
    }
    
    private void limpiarDatos( )
    {
        txtNombre.clear( );
        txtContenido.clear( );
        txtTipo.clear( );
        hide( );
    }
}
