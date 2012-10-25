package co.edu.unicesi.sami.client.home;

import co.edu.unicesi.sami.bo.ObjetivoGeneralBO;
import co.edu.unicesi.sami.client.home.Mensajero;
import co.edu.unicesi.sami.client.competencias.CompetenciasService;
import co.edu.unicesi.sami.client.competencias.CompetenciasServiceAsync;
import co.edu.unicesi.sami.client.controller.DTEvent;
import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TabObjGeneral extends TabItem
{
    private final static MultiLingualConstants MultiLingualConstants = GWT.create( MultiLingualConstants.class );
    private final CompetenciasServiceAsync competenciasService = GWT.create( CompetenciasService.class );

    private int idObjGeneral;

    private TextArea txtObjGeneral;
    private Text labObjGeneral;
    private Button btnGuardar;

    public TabObjGeneral( )
    {
        setText( MultiLingualConstants.tabObjGeneral_text( ) );
        setSize( 800, 600 );

        LayoutContainer container = new LayoutContainer( );
        container.setLayout( new AbsoluteLayout( ) );

        txtObjGeneral = new TextArea( );
        txtObjGeneral.setSize( "600px", "150px" );
        container.add( txtObjGeneral, new AbsoluteData( 100, 50 ) );

        labObjGeneral = new Text( MultiLingualConstants.labObjGeneral_text( ) );
        container.add( labObjGeneral, new AbsoluteData(100, 30 ) );

        btnGuardar = new Button( MultiLingualConstants.btnAgregar_text( ) );
        container.add( btnGuardar, new AbsoluteData( 375, 210 ) );

        add( container );

        eventoCargarTab( );
        eventoAgregarObjGeneral( );
        eventoEditarObjGeneral( );
    }

    private void eventoCargarTab( )
    {
        this.addListener( Events.Select, new Listener<BaseEvent>( )
        {
            @Override
            public void handleEvent( BaseEvent be )
            {
                Dispatcher.forwardEvent( DTEvent.ACTUALIZAR_OBJ_GENERAL );
            }
        } );
    }

    private void eventoAgregarObjGeneral( )
    {
        btnGuardar.addSelectionListener( new SelectionListener<ButtonEvent>( )
        {

            @Override
            public void componentSelected( ButtonEvent ce )
            {
                if( btnGuardar.getText( ).equals( MultiLingualConstants.btnAgregar_text( ) ) )
                {
                    agregarObjGeneral( );
                }
            }
        } );
    }

    private void eventoEditarObjGeneral( )
    {
        btnGuardar.addSelectionListener( new SelectionListener<ButtonEvent>( )
        {
            @Override
            public void componentSelected( ButtonEvent ce )
            {
                if( btnGuardar.getText( ).equals( MultiLingualConstants.btnEditar_text( ) ) )
                {
                    editarObjGeneral( );
                }
            }
        } );
    }

    private void agregarObjGeneral( )
    {
        ObjetivoGeneralBO objGeneral = new ObjetivoGeneralBO( );
        objGeneral.setContenido( txtObjGeneral.getValue( ) );
        objGeneral.setIdCurso( ( Integer )Registry.get( "idCurso" ) );

        competenciasService.agregarObjGeneral( objGeneral, new AsyncCallback<Integer>( )
        {
            @Override
            public void onSuccess( Integer result )
            {
                Dispatcher.forwardEvent( DTEvent.ACTUALIZAR_OBJ_GENERAL );
                Info.display( "sami", Mensajero.mostrarMensaje( result ) );
            }

            @Override
            public void onFailure( Throwable caught )
            {
                Info.display( "Error", Mensajero.ON_FAILURE );
            }
        } );
    }

    private void editarObjGeneral( )
    {
        ObjetivoGeneralBO objGeneral = new ObjetivoGeneralBO( );
        objGeneral.setId( idObjGeneral );
        objGeneral.setContenido( txtObjGeneral.getValue( ) );
        objGeneral.setIdCurso( ( Integer )Registry.get( "idCurso" ) );

        competenciasService.editarObjGeneral( objGeneral, new AsyncCallback<Integer>( )
        {
            @Override
            public void onSuccess( Integer result )
            {
                Info.display( "sami", Mensajero.mostrarMensaje( result ) );
            }

            @Override
            public void onFailure( Throwable caught )
            {
                Info.display( "Error", Mensajero.ON_FAILURE );
            }
        } );
    }

    public void asignarObjGeneral( )
    {
        if( Registry.get( "idCurso" ) != null )
        {
            int idCurso = Registry.get( "idCurso" );
            competenciasService.buscarObjGeneral( idCurso, new AsyncCallback<ObjetivoGeneralBO>( )
            {
                @Override
                public void onSuccess( ObjetivoGeneralBO objGeneral )
                {
                    if( objGeneral != null )
                    {
                        txtObjGeneral.setValue( objGeneral.getContenido( ) );
                        idObjGeneral = objGeneral.getId( );
                        btnGuardar.setText( MultiLingualConstants.btnEditar_text( ) );
                    }
                    else
                    {
                        btnGuardar.setText( MultiLingualConstants.btnAgregar_text( ) );
                        txtObjGeneral.clear( );
                    }
                }

                @Override
                public void onFailure( Throwable caught )
                {
                    Info.display( "Error", Mensajero.ON_FAILURE );
                }
            } );
        }
    }
}
