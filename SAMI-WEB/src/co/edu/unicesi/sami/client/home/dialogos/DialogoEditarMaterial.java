package co.edu.unicesi.sami.client.home.dialogos;

import co.edu.unicesi.sami.client.home.TabMateriales;
import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;
import co.edu.unicesi.sami.client.model.MaterialModel;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.google.gwt.core.client.GWT;

public class DialogoEditarMaterial extends Dialog
{

    private final static MultiLingualConstants MultiLingualConstants = GWT.create( MultiLingualConstants.class );

    private TabMateriales tabMateriales;
    
    private Text labNombre;
    private TextArea txtNombre;

    public DialogoEditarMaterial( TabMateriales tabMateriales )
    {
        this.tabMateriales = tabMateriales;
        
        setModal( true );
        setSize( 500, 300 );
        setHeading( MultiLingualConstants.dialogoEditarMaterial_heading( ) );
        setLayout( new AbsoluteLayout( ) );

        txtNombre = new TextArea( );
        txtNombre.setSize( "212px", "60px" );
        add( txtNombre, new AbsoluteData( 157, 110 ) );
        
        labNombre = new Text( MultiLingualConstants.labNombre_text( ) );
        add( labNombre, new AbsoluteData( 30, 110 ) );
        
        eventoEditarMaterial( );
    }    
    
    private void eventoEditarMaterial( )
    {
        getButtonById( OK ).addSelectionListener( new SelectionListener<ButtonEvent>( )
        {
            @Override
            public void componentSelected( ButtonEvent ce )
            {
                editarMaterial( );
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

    private void editarMaterial( )
    {
        String nombre = txtNombre.getValue( );       
        
        tabMateriales.editarMaterial( nombre);
    }

    public void cargarDatos( MaterialModel material )
    {
        txtNombre.setValue( material.getNombre( ) );
        show( );
    }

    private void limpiarDatos( )
    {
        txtNombre.clear( );
        hide( );
    }
}
