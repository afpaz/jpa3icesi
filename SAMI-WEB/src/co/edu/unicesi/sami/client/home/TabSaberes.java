package co.edu.unicesi.sami.client.home;

import java.util.ArrayList;
import java.util.List;

import org.apache.xerces.impl.dtd.models.CMUniOp;

import co.edu.unicesi.sami.bo.ObjetivoEspecificoBO;
import co.edu.unicesi.sami.bo.SaberBO;
import co.edu.unicesi.sami.client.home.Mensajero;
import co.edu.unicesi.sami.client.competencias.CompetenciasService;
import co.edu.unicesi.sami.client.competencias.CompetenciasServiceAsync;
import co.edu.unicesi.sami.client.controller.DTEvent;
import co.edu.unicesi.sami.client.home.dialogos.DialogoAgregarSaber;
import co.edu.unicesi.sami.client.home.dialogos.DialogoEditarSaber;
import co.edu.unicesi.sami.client.listados.ListadosService;
import co.edu.unicesi.sami.client.listados.ListadosServiceAsync;
import co.edu.unicesi.sami.client.model.CursoModel;
import co.edu.unicesi.sami.client.model.MetaTerminalModel;
import co.edu.unicesi.sami.client.model.ObjetivoEspecificoModel;
import co.edu.unicesi.sami.client.model.SaberModel;
import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;
import co.edu.unicesi.sami.client.planificador.PlanificadorService;
import co.edu.unicesi.sami.client.planificador.PlanificadorServiceAsync;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TabSaberes extends TabItem
{

    private final static MultiLingualConstants MultiLingualConstants = GWT.create( MultiLingualConstants.class );
    private final PlanificadorServiceAsync planificadorService = GWT.create( PlanificadorService.class );
    private final ListadosServiceAsync listadosService = GWT.create( ListadosService.class );

    private DialogoAgregarSaber dialogoAgregarSaber;
    private DialogoEditarSaber dialogoEditarSaber;

    private int idObjEspecifico;
    private int idSaber;

    private Grid<ObjetivoEspecificoModel> gridObjEspecifico;
    private Grid<SaberModel> gridSaberes;
    private Button btnAgregar;

    public TabSaberes( )
    {
        setText( MultiLingualConstants.tabSaberes_text( ) );
        setSize( 800, 600 );

        LayoutContainer container = new LayoutContainer( );
        container.setLayout( new AbsoluteLayout( ) );

        gridObjEspecifico = new Grid<ObjetivoEspecificoModel>( new ListStore<ObjetivoEspecificoModel>( ), getColumnModelObjEspecificos( ) );
        gridObjEspecifico.setBorders( true );
        gridObjEspecifico.setStripeRows( true );

        ContentPanel cpObjEspecificos = new ContentPanel( );
        cpObjEspecificos.setBodyBorder( false );
        cpObjEspecificos.setHeading( MultiLingualConstants.tableObjEspecificos_heading( ) );
        cpObjEspecificos.setButtonAlign( HorizontalAlignment.CENTER );
        cpObjEspecificos.setLayout( new FitLayout( ) );
        cpObjEspecificos.setSize( 600, 175 );
        cpObjEspecificos.add( gridObjEspecifico );

        container.add( cpObjEspecificos, new AbsoluteData( 100, 30 ) );

        gridSaberes = new Grid<SaberModel>( new ListStore<SaberModel>( ), getColumnModelSaberes( ) );
        gridSaberes.setBorders( true );
        gridSaberes.setStripeRows( true );

        ContentPanel cpSaberes = new ContentPanel( );
        cpSaberes.setBodyBorder( false );
        cpSaberes.setHeading( MultiLingualConstants.tableSaberes_heading( ) );
        cpSaberes.setButtonAlign( HorizontalAlignment.CENTER );
        cpSaberes.setLayout( new FitLayout( ) );
        cpSaberes.setSize( 600, 275 );
        cpSaberes.add( gridSaberes );

        container.add( cpSaberes, new AbsoluteData( 100, 225 ) );

        btnAgregar = new Button( MultiLingualConstants.btnAgregar_text( ) );
        container.add( btnAgregar, new AbsoluteData( 375, 510 ) );

        add( container );

        inicializarDialogos( );

        eventoCargarTab( );
        eventoSeleccionarObjEspecifico( );
        eventoAgregarSaber( );
        eventoEditarSaber( );
    }

    private void inicializarDialogos( )
    {
        dialogoAgregarSaber = new DialogoAgregarSaber( this );
        dialogoEditarSaber = new DialogoEditarSaber( this );
    }

    private void eventoCargarTab( )
    {
        this.addListener( Events.Select, new Listener<BaseEvent>( )
        {
            @Override
            public void handleEvent( BaseEvent be )
            {
                idSaber = 0;
                idObjEspecifico = 0;
                
                cargarObjetivosEspecificos( );
                cargarSaberes( );
            }
        } );
    }

    private void eventoSeleccionarObjEspecifico( )
    {
        gridObjEspecifico.addListener( Events.OnClick, new Listener<GridEvent<ObjetivoEspecificoModel>>( )
        {
            public void handleEvent( GridEvent<ObjetivoEspecificoModel> be )
            {
                ObjetivoEspecificoModel model = be.getGrid( ).getSelectionModel( ).getSelectedItem( );
                idObjEspecifico = model.getId( );
                cargarSaberesPorObjEspecifico( );
                Info.display( "sami", MultiLingualConstants.msgSeleccionarObjEspecifico( ) + " " + model.getNombre( ) );
            }
        } );
    }

    private void eventoAgregarSaber( )
    {
        btnAgregar.addSelectionListener( new SelectionListener<ButtonEvent>( )
        {
            @Override
            public void componentSelected( ButtonEvent ce )
            {
                if( idObjEspecifico != 0 )
                {
                    dialogoAgregarSaber.show( );
                }
                else
                {
                    Info.display( "sami", MultiLingualConstants.msgObjEspecifico( ) );
                }
            }
        } );
    }

    private void eventoEditarSaber( )
    {
        gridSaberes.addListener( Events.CellDoubleClick, new Listener<GridEvent<SaberModel>>( )
        {
            public void handleEvent( GridEvent<SaberModel> be )
            {
                SaberModel saber = be.getGrid( ).getSelectionModel( ).getSelectedItem( );
                dialogoEditarSaber.cargarDatos( saber );
                idSaber = saber.getId( );
            }
        } );
    }

    public void agregarSaber( String nombre, String tipo, String contenido )
    {
        SaberBO saber = new SaberBO( );
        saber.setNombre( nombre );
        saber.setContenido( contenido );
        saber.setTipo( tipo );
        saber.setIdObjEspecifico( idObjEspecifico );

        planificadorService.agregarSaber( saber, new AsyncCallback<Integer>( )
        {
            @Override
            public void onSuccess( Integer result )
            {
                cargarSaberesPorObjEspecifico( );
                Info.display( "sami", Mensajero.mostrarMensaje( result ) );
            }

            @Override
            public void onFailure( Throwable caught )
            {
                Info.display( "Error", Mensajero.ON_FAILURE );
            }
        } );
    }

    public void editarSaber( String nombre, String tipo, String contenido )
    {
        SaberBO saber = new SaberBO( );
        saber.setId( idSaber );
        saber.setNombre( nombre );
        saber.setContenido( contenido );
        saber.setTipo( tipo );

        planificadorService.editarSaber( saber, new AsyncCallback<Integer>( )
        {
            @Override
            public void onSuccess( Integer result )
            {
                if( idObjEspecifico == 0 )
                    cargarSaberes( );
                else
                    cargarSaberesPorObjEspecifico( );
                Info.display( "sami", Mensajero.mostrarMensaje( result ) );
            }

            @Override
            public void onFailure( Throwable caught )
            {
                Info.display( "Error", Mensajero.ON_FAILURE );
            }
        } );
    }

    private void cargarObjetivosEspecificos( )
    {
        int idCurso = Registry.get( "idCurso" );
        listadosService.listarObjEspecificosPorCurso( idCurso, new AsyncCallback<List<ObjetivoEspecificoBO>>( )
        {
            @Override
            public void onSuccess( List<ObjetivoEspecificoBO> result )
            {
                Dispatcher.forwardEvent( DTEvent.LISTAR_OBJ_ESPECIFICOS, result );
            }

            @Override
            public void onFailure( Throwable caught )
            {
                Info.display( "Error", Mensajero.ON_FAILURE );
            }

        } );
    }

    private void cargarSaberes( )
    {
        int idCurso = Registry.get( "idCurso" );
        listadosService.listarSaberesPorCurso( idCurso, new AsyncCallback<List<SaberBO>>( )
        {
            @Override
            public void onSuccess( List<SaberBO> result )
            {
                Dispatcher.forwardEvent( DTEvent.LISTAR_SABERES, result );
            }

            @Override
            public void onFailure( Throwable caught )
            {
                Info.display( "Error", Mensajero.ON_FAILURE );
            }
        } );
    }

    private void cargarSaberesPorObjEspecifico( )
    {
        listadosService.listarSaberesPorObjetivoEspecifico( idObjEspecifico, new AsyncCallback<List<SaberBO>>( )
        {
            @Override
            public void onSuccess( List<SaberBO> result )
            {
                Dispatcher.forwardEvent( DTEvent.LISTAR_SABERES_POR_OBJ_ESPECIFICO, result );
            }

            @Override
            public void onFailure( Throwable caught )
            {
                Info.display( "Error", Mensajero.ON_FAILURE );
            }
        } );
    }

    public void actualizarTablaObjEspecificos( ListStore<ObjetivoEspecificoModel> objEspecificos )
    {
        gridObjEspecifico.reconfigure( objEspecificos, getColumnModelObjEspecificos( ) );
    }

    public void actualizarTablaSaberes( ListStore<SaberModel> saberes )
    {
        gridSaberes.reconfigure( saberes, getColumnModelSaberes( ) );
    }

    private ColumnModel getColumnModelObjEspecificos( )
    {
        List<ColumnConfig> configsObj = new ArrayList<ColumnConfig>( );

        ColumnConfig columnObj = new ColumnConfig( "nombre", MultiLingualConstants.columnObjEspecificos_nombre( ), 50 );
        columnObj.setAlignment( HorizontalAlignment.LEFT );
        configsObj.add( columnObj );

        columnObj = new ColumnConfig( "contenido", MultiLingualConstants.columnObjEspecificos_contenido( ), 525 );
        columnObj.setAlignment( HorizontalAlignment.LEFT );
        configsObj.add( columnObj );

        return new ColumnModel( configsObj );
    }

    private ColumnModel getColumnModelSaberes( )
    {
        List<ColumnConfig> configSaberes = new ArrayList<ColumnConfig>( );

        ColumnConfig columnSaberes = new ColumnConfig( "nombre", MultiLingualConstants.columnSaberes_nombre( ), 50 );
        columnSaberes.setAlignment( HorizontalAlignment.LEFT );
        configSaberes.add( columnSaberes );

        columnSaberes = new ColumnConfig( "tipo", MultiLingualConstants.columnSaberes_tipo( ), 50 );
        columnSaberes.setAlignment( HorizontalAlignment.LEFT );
        configSaberes.add( columnSaberes );

        columnSaberes = new ColumnConfig( "contenido", MultiLingualConstants.columnSaberes_contenido( ), 475 );
        columnSaberes.setAlignment( HorizontalAlignment.LEFT );
        configSaberes.add( columnSaberes );

        return new ColumnModel( configSaberes );
    }
}
