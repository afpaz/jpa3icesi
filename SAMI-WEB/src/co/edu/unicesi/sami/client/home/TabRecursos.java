package co.edu.unicesi.sami.client.home;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicesi.sami.bo.MaterialBO;
import co.edu.unicesi.sami.bo.RecursoBO;
import co.edu.unicesi.sami.bo.SaberBO;
import co.edu.unicesi.sami.client.home.Mensajero;
import co.edu.unicesi.sami.client.controller.DTEvent;
import co.edu.unicesi.sami.client.listados.ListadosService;
import co.edu.unicesi.sami.client.listados.ListadosServiceAsync;
import co.edu.unicesi.sami.client.model.CursoModel;
import co.edu.unicesi.sami.client.model.MaterialModel;
import co.edu.unicesi.sami.client.model.ObjetivoEspecificoModel;
import co.edu.unicesi.sami.client.model.SaberModel;
import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;
import co.edu.unicesi.sami.client.planificador.PlanificadorService;
import co.edu.unicesi.sami.client.planificador.PlanificadorServiceAsync;

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
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style;

public class TabRecursos extends TabItem
{
    private final static MultiLingualConstants MultiLingualConstants = GWT.create( MultiLingualConstants.class );
    private final ListadosServiceAsync listadosService = GWT.create( ListadosService.class );
    private final PlanificadorServiceAsync planificadorService = GWT.create( PlanificadorService.class );

    private int idSaber;
    private int idMaterial;

    private Grid<SaberModel> gridSaberes;
    private Grid<MaterialModel> gridMateriales;
    private Grid<MaterialModel> gridRecursos;
    private Button btnAsociar;

    public TabRecursos( )
    {
        setText( MultiLingualConstants.tabRecursos_text( ) );
        setSize( 800, 600 );

        LayoutContainer container = new LayoutContainer( );
        container.setLayout( new AbsoluteLayout( ) );

        gridSaberes = new Grid<SaberModel>( new ListStore<SaberModel>( ), getColumnModelSaberes( ) );
        gridSaberes.setBorders( true );
        gridSaberes.setStripeRows( true );

        ContentPanel cpSaberes = new ContentPanel( );
        cpSaberes.setBodyBorder( false );
        cpSaberes.setHeading( MultiLingualConstants.tableSaberes_heading( ) );
        cpSaberes.setButtonAlign( HorizontalAlignment.CENTER );
        cpSaberes.setLayout( new FitLayout( ) );
        cpSaberes.setSize( 325, 175 );
        cpSaberes.add( gridSaberes );
        container.add( cpSaberes, new AbsoluteData( 50, 30 ) );

        gridMateriales = new Grid<MaterialModel>( new ListStore<MaterialModel>( ), getColumnModelMateriales( ) );     
        gridMateriales.setBorders( true );
        gridMateriales.setStripeRows( true );

        ContentPanel cpMateriales = new ContentPanel( );
        cpMateriales.setBodyBorder( false );
        cpMateriales.setHeading( MultiLingualConstants.tableMateriales_heading( ) );
        cpMateriales.setButtonAlign( HorizontalAlignment.CENTER );
        cpMateriales.setLayout( new FitLayout( ) );
        cpMateriales.setSize( 325, 175 );
        cpMateriales.add( gridMateriales );
        container.add( cpMateriales, new AbsoluteData( 425, 30 ) );

        gridRecursos = new Grid<MaterialModel>( new ListStore<MaterialModel>( ), getColumnModelRecursos( ) );
        gridRecursos.setBorders( true );
        gridRecursos.setStripeRows( true );

        ContentPanel cpRecursos = new ContentPanel( );
        cpRecursos.setBodyBorder( false );
        cpRecursos.setHeading( MultiLingualConstants.tableRecursos_heading( ) );
        cpRecursos.setButtonAlign( HorizontalAlignment.CENTER );
        cpRecursos.setLayout( new FitLayout( ) );
        cpRecursos.setSize( 600, 275 );
        cpRecursos.add( gridRecursos );
        container.add( cpRecursos, new AbsoluteData( 100, 275 ) );

        btnAsociar = new Button( MultiLingualConstants.btnAsociar_text( ) );
        container.add( btnAsociar, new AbsoluteData( 375, 225 ) );

        add( container );
        
        eventoCargarTab( );
        eventoSeleccionarSaber( );
        eventoSeleccionarMaterial( );
        eventoAgregarRecurso( );
    }

    private void eventoCargarTab( )
    {
        this.addListener( Events.Select, new Listener<BaseEvent>( )
        {
            @Override
            public void handleEvent( BaseEvent be )
            {
                idSaber = 0;
                idMaterial = 0;

                cargarMateriales( );
                cargarSaberes( );
            }
        } );
    }

    private void eventoSeleccionarSaber( )
    {
        gridSaberes.addListener( Events.OnClick, new Listener<GridEvent<SaberModel>>( )
        {
            public void handleEvent( GridEvent<SaberModel> be )
            {
                SaberModel model = be.getGrid( ).getSelectionModel( ).getSelectedItem( );
                idSaber = model.getId( );
                cargarRecursosPorSaber( );
                Info.display( "sami", MultiLingualConstants.msgSeleccionarSaber( ) + " " + model.getNombre( ) );
            }
        } );
    }

    private void eventoSeleccionarMaterial( )
    {
        gridMateriales.addListener( Events.OnClick, new Listener<GridEvent<MaterialModel>>( )
        {
            public void handleEvent( GridEvent<MaterialModel> be )
            {
                MaterialModel model = be.getGrid( ).getSelectionModel( ).getSelectedItem( );
                idMaterial = model.getId( );
                Info.display( "sami", MultiLingualConstants.msgSeleccionarMaterial( ) + " " + model.getNombre( ) );
            }
        } );
    }

    private void eventoAgregarRecurso( )
    {
        btnAsociar.addSelectionListener( new SelectionListener<ButtonEvent>( )
        {
            @Override
            public void componentSelected( ButtonEvent ce )
            {
                if( idSaber != 0 && idMaterial != 0 )
                {
                    agregarRecurso( );
                }
                else
                {
                    Info.display( "sami", MultiLingualConstants.msgRecurso( ) );
                }
            }
        } );
    }

    private void agregarRecurso( )
    {
        RecursoBO recurso = new RecursoBO( );
        recurso.setIdMaterial( idMaterial );
        recurso.setIdSaber( idSaber );

        planificadorService.agregarRecurso( recurso, new AsyncCallback<Integer>( )
        {
            @Override
            public void onSuccess( Integer result )
            {
                cargarRecursosPorSaber( );
                Info.display( "sami", Mensajero.mostrarMensaje( result ) );
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

    private void cargarMateriales( )
    {
        int idCurso = Registry.get( "idCurso" );
        listadosService.listarMaterialesPorCurso( idCurso, new AsyncCallback<List<MaterialBO>>( )
        {
            @Override
            public void onSuccess( List<MaterialBO> result )
            {
                Dispatcher.forwardEvent( DTEvent.LISTAR_MATERIALES, result );
            }

            @Override
            public void onFailure( Throwable caught )
            {
                Info.display( "Error", Mensajero.ON_FAILURE );
            }
        } );
    }

    private void cargarRecursosPorSaber( )
    {
        listadosService.listarRecursosPorSaber( idSaber, new AsyncCallback<List<RecursoBO>>( )
        {
            @Override
            public void onSuccess( List<RecursoBO> result )
            {
                Dispatcher.forwardEvent( DTEvent.LISTAR_RECURSOS_POR_SABER, result );
            }

            @Override
            public void onFailure( Throwable caught )
            {
                Info.display( "Error", Mensajero.ON_FAILURE );
            }

        } );
    }

    public void actualizarTablaSaberes( ListStore<SaberModel> saberes )
    {

        gridSaberes.reconfigure( saberes, getColumnModelSaberes( ) );
    }

    public void actualizarTablaMateriales( ListStore<MaterialModel> materiales )
    {
        gridMateriales.reconfigure( materiales, getColumnModelMateriales( ) );
    }

    public void actualizarTablaRecursos( ListStore<MaterialModel> materiales )
    {
        gridRecursos.reconfigure( materiales, getColumnModelRecursos( ) );
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

        columnSaberes = new ColumnConfig( "contenido", MultiLingualConstants.columnSaberes_contenido( ), 200 );
        columnSaberes.setAlignment( HorizontalAlignment.LEFT );
        configSaberes.add( columnSaberes );

        return new ColumnModel( configSaberes );
    }

    private ColumnModel getColumnModelMateriales( )
    {
        List<ColumnConfig> configsMateriales = new ArrayList<ColumnConfig>( );

        ColumnConfig columnObj = new ColumnConfig( "nombre", MultiLingualConstants.columnObjEspecificos_nombre( ), 300 );
        columnObj.setAlignment( HorizontalAlignment.LEFT );
        configsMateriales.add( columnObj );

        return new ColumnModel( configsMateriales );
    }
    
    private ColumnModel getColumnModelRecursos( )
    {
        List<ColumnConfig> configsMateriales = new ArrayList<ColumnConfig>( );

        ColumnConfig columnObj = new ColumnConfig( "nombre", MultiLingualConstants.columnObjEspecificos_nombre( ), 575 );
        columnObj.setAlignment( HorizontalAlignment.LEFT );
        configsMateriales.add( columnObj );

        return new ColumnModel( configsMateriales );
    }
}
