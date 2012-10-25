package co.edu.unicesi.sami.client.home;

import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import co.edu.unicesi.sami.bo.MetaTerminalBO;
import co.edu.unicesi.sami.bo.ObjetivoEspecificoBO;
import co.edu.unicesi.sami.client.home.Mensajero;
import co.edu.unicesi.sami.client.competencias.CompetenciasService;
import co.edu.unicesi.sami.client.competencias.CompetenciasServiceAsync;
import co.edu.unicesi.sami.client.controller.DTEvent;
import co.edu.unicesi.sami.client.home.dialogos.DialogoAgregarObjEspecifico;
import co.edu.unicesi.sami.client.home.dialogos.DialogoEditarObjEspecifico;
import co.edu.unicesi.sami.client.listados.ListadosService;
import co.edu.unicesi.sami.client.listados.ListadosServiceAsync;
import co.edu.unicesi.sami.client.model.CursoModel;
import co.edu.unicesi.sami.client.model.MetaTerminalModel;
import co.edu.unicesi.sami.client.model.ObjetivoEspecificoModel;
import co.edu.unicesi.sami.client.model.ObjetivoTerminalModel;
import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;

public class TabObjEspecificos extends TabItem
{
    private final static MultiLingualConstants MultiLingualConstants = GWT.create( MultiLingualConstants.class );
    private final CompetenciasServiceAsync competenciasService = GWT.create( CompetenciasService.class );
    private final ListadosServiceAsync listadosService = GWT.create( ListadosService.class );

    private DialogoAgregarObjEspecifico dialogoAgregarObjEspecifico;
    private DialogoEditarObjEspecifico dialogoEditarObjEspecifico;

    private int idMetaTerminal;
    private int idObjEspecifico;

    private Grid<ObjetivoEspecificoModel> gridObjEspecificos;
    private Grid<MetaTerminalModel> gridMetasTerminales;
    private Button btnAgregar;

    public TabObjEspecificos( )
    {
        setText( MultiLingualConstants.tabObjEspecificos_text( ) );
        setSize( 800, 600 );

        LayoutContainer container = new LayoutContainer( );
        container.setLayout( new AbsoluteLayout( ) );

        gridMetasTerminales = new Grid<MetaTerminalModel>( new ListStore<MetaTerminalModel>( ), getColumnModelMetasTerminales( ) );
        gridMetasTerminales.setBorders( true );
        gridMetasTerminales.setStripeRows( true );

        ContentPanel cpUnidad = new ContentPanel( );
        cpUnidad.setBodyBorder( false );
        cpUnidad.setHeading( MultiLingualConstants.tableMetasTerminales_heading( ) );
        cpUnidad.setButtonAlign( HorizontalAlignment.CENTER );
        cpUnidad.setLayout( new FitLayout( ) );
        cpUnidad.setSize( 600, 175 );
        cpUnidad.add( gridMetasTerminales );
        container.add( cpUnidad, new AbsoluteData( 100, 30 ) );

        gridObjEspecificos = new Grid<ObjetivoEspecificoModel>( new ListStore<ObjetivoEspecificoModel>( ), getColumnModelObjEspecificos( ) );
        gridObjEspecificos.setBorders( true );
        gridObjEspecificos.setStripeRows( true );

        ContentPanel cpObj = new ContentPanel( );
        cpObj.setBodyBorder( false );
        cpObj.setHeading( MultiLingualConstants.tableObjEspecificos_heading( ) );
        cpObj.setButtonAlign( HorizontalAlignment.CENTER );
        cpObj.setLayout( new FitLayout( ) );
        cpObj.setSize( 600, 275 );
        cpObj.add( gridObjEspecificos );

        container.add( cpObj, new AbsoluteData( 100, 225 ) );

        btnAgregar = new Button( MultiLingualConstants.btnAgregar_text( ) );
        container.add( btnAgregar, new AbsoluteData( 375, 510 ) );

        add( container );

        inicializarDialogos( );

        eventoCargarTab( );
        eventoSeleccionarMetaTerminal( );
        eventoAgregarObjEspecifico( );
        eventoEditarObjEspecifico( );
    }

    private void inicializarDialogos( )
    {
        dialogoAgregarObjEspecifico = new DialogoAgregarObjEspecifico( this );
        dialogoEditarObjEspecifico = new DialogoEditarObjEspecifico( this );
    }

    private void eventoCargarTab( )
    {
        this.addListener( Events.Select, new Listener<BaseEvent>( )
        {
            @Override
            public void handleEvent( BaseEvent be )
            {
                idMetaTerminal = 0;
                idObjEspecifico = 0;
                
                cargarMetasTerminales( );
                cargarObjetivosEspecificos( );
            }
        } );
    }

    private void eventoSeleccionarMetaTerminal( )
    {
        gridMetasTerminales.addListener( Events.OnClick, new Listener<GridEvent<MetaTerminalModel>>( )
        {
            public void handleEvent( GridEvent<MetaTerminalModel> be )
            {
                MetaTerminalModel model = be.getGrid( ).getSelectionModel( ).getSelectedItem( );
                idMetaTerminal = model.getId( );
                cargarObjEspecificosPorMetaTerminal( );
                Info.display( "sami", MultiLingualConstants.msgSeleccionarMetaTerminal( ) + " " + model.getNombreUnidad( ) + " - " + model.getNombreObjTerminal( ) );
            }
        } );
    }

    private void eventoAgregarObjEspecifico( )
    {
        btnAgregar.addSelectionListener( new SelectionListener<ButtonEvent>( )
        {
            @Override
            public void componentSelected( ButtonEvent ce )
            {
                if( idMetaTerminal != 0 )
                {
                    dialogoAgregarObjEspecifico.show( );
                }
                else
                {
                    Info.display( "sami", MultiLingualConstants.msgMetaTerminal( ) );
                }
            }
        } );
    }

    private void eventoEditarObjEspecifico( )
    {
        gridObjEspecificos.addListener( Events.CellDoubleClick, new Listener<GridEvent<ObjetivoEspecificoModel>>( )
        {
            public void handleEvent( GridEvent<ObjetivoEspecificoModel> be )
            {
                ObjetivoEspecificoModel objEspecifico = be.getGrid( ).getSelectionModel( ).getSelectedItem( );
                dialogoEditarObjEspecifico.cargarDatos( objEspecifico );
                idObjEspecifico = objEspecifico.getId( );
            }
        } );
    }

    public void agregarObjEspecifico( String nombre, String contenido )
    {
        ObjetivoEspecificoBO objEspecifico = new ObjetivoEspecificoBO( );
        objEspecifico.setNombre( nombre );
        objEspecifico.setContenido( contenido );
        objEspecifico.setIdMetaTerminal( idMetaTerminal );

        competenciasService.agregarObjEspecifico( objEspecifico, new AsyncCallback<Integer>( )
        {
            @Override
            public void onSuccess( Integer result )
            {
                cargarObjEspecificosPorMetaTerminal( );
                Info.display( "sami", Mensajero.mostrarMensaje( result ) );
            }

            @Override
            public void onFailure( Throwable caught )
            {
                Info.display( "Error", Mensajero.ON_FAILURE );
            }
        } );
    }

    public void editarObjEspecifico( String nombre, String contenido )
    {
        ObjetivoEspecificoBO objEspecifico = new ObjetivoEspecificoBO( );
        objEspecifico.setId( idObjEspecifico );
        objEspecifico.setNombre( nombre );
        objEspecifico.setContenido( contenido );

        competenciasService.editarObjEspecifico( objEspecifico, new AsyncCallback<Integer>( )
        {
            @Override
            public void onSuccess( Integer result )
            {

                if( idMetaTerminal == 0 )
                    cargarObjetivosEspecificos( );
                else
                    cargarObjEspecificosPorMetaTerminal( );
                
                Info.display( "sami", Mensajero.mostrarMensaje( result ) );
            }

            @Override
            public void onFailure( Throwable caught )
            {
                Info.display( "Error", Mensajero.ON_FAILURE );
            }
        } );
    }
    private void cargarMetasTerminales( )
    {
        int idCurso = Registry.get( "idCurso" );
        listadosService.listarMetasTerminalesPorCurso( idCurso, new AsyncCallback<List<MetaTerminalBO>>( )
        {
            @Override
            public void onSuccess( List<MetaTerminalBO> result )
            {
                Dispatcher.forwardEvent( DTEvent.LISTAR_METAS_TERMINALES, result );
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

    private void cargarObjEspecificosPorMetaTerminal( )
    {
        listadosService.listarObjEspecificosPorMetaTerminal( idMetaTerminal, new AsyncCallback<List<ObjetivoEspecificoBO>>( )
        {

            @Override
            public void onSuccess( List<ObjetivoEspecificoBO> result )
            {
                Dispatcher.forwardEvent( DTEvent.LISTAR_OBJ_ESPECIFICOS_POR_META_TERMINAL, result );
            }

            @Override
            public void onFailure( Throwable caught )
            {
                Info.display( "Error", Mensajero.ON_FAILURE );
            }
        } );
    }

    public void actualizarTablaMetasTerminales( ListStore<MetaTerminalModel> metasTerminales )
    {
        gridMetasTerminales.reconfigure( metasTerminales, getColumnModelMetasTerminales( ) );
    }

    public void actualizarTablaObjEspecificos( ListStore<ObjetivoEspecificoModel> objEspecificos )
    {
        gridObjEspecificos.reconfigure( objEspecificos, getColumnModelObjEspecificos( ) );
    }

    private ColumnModel getColumnModelMetasTerminales( )
    {
        List<ColumnConfig> configsMetaTerminal = new ArrayList<ColumnConfig>( );

        ColumnConfig columnMetaTerminal = new ColumnConfig( "nombreUnidad", MultiLingualConstants.columnMetasTerminales_nombreUnidad( ), 50 );
        columnMetaTerminal.setAlignment( HorizontalAlignment.CENTER );
        configsMetaTerminal.add( columnMetaTerminal );

        columnMetaTerminal = new ColumnConfig( "contenidoUnidad", MultiLingualConstants.columnMetasTerminales_contenidoUnidad( ), 225 );
        columnMetaTerminal.setAlignment( HorizontalAlignment.LEFT );
        configsMetaTerminal.add( columnMetaTerminal );

        columnMetaTerminal = new ColumnConfig( "nombreObjTerminal", MultiLingualConstants.columnMetasTerminales_nombreObjTerminal( ), 50 );
        columnMetaTerminal.setAlignment( HorizontalAlignment.CENTER );
        configsMetaTerminal.add( columnMetaTerminal );

        columnMetaTerminal = new ColumnConfig( "contenidoObjTerminal", MultiLingualConstants.columnMetasTerminales_contenidoObjTerminal( ), 245 );
        columnMetaTerminal.setAlignment( HorizontalAlignment.LEFT );
        configsMetaTerminal.add( columnMetaTerminal );

        return new ColumnModel( configsMetaTerminal );
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
}
