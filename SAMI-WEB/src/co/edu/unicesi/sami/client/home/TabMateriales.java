package co.edu.unicesi.sami.client.home;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicesi.sami.bo.ObjetivoGeneralBO;
import co.edu.unicesi.sami.bo.MaterialBO;
import co.edu.unicesi.sami.client.home.Mensajero;
import co.edu.unicesi.sami.client.controller.DTEvent;
import co.edu.unicesi.sami.client.home.dialogos.DialogoAgregarMaterial;
import co.edu.unicesi.sami.client.home.dialogos.DialogoEditarMaterial;
import co.edu.unicesi.sami.client.listados.ListadosService;
import co.edu.unicesi.sami.client.listados.ListadosServiceAsync;
import co.edu.unicesi.sami.client.model.CursoModel;
import co.edu.unicesi.sami.client.model.MaterialModel;
import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;
import co.edu.unicesi.sami.client.planificador.PlanificadorService;
import co.edu.unicesi.sami.client.planificador.PlanificadorServiceAsync;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TabMateriales extends TabItem
{
    private final static MultiLingualConstants MultiLingualConstants = GWT.create( MultiLingualConstants.class );
    private final ListadosServiceAsync listadosService = GWT.create( ListadosService.class );
    private final PlanificadorServiceAsync planificadorService = GWT.create( PlanificadorService.class );

    private int idMaterial;
    
    private DialogoAgregarMaterial dialogoAgregarMaterial;
    private DialogoEditarMaterial dialogoEditarMaterial;
    
    private Grid<MaterialModel> gridMateriales;
    private Button btnAgregar;
    
    public TabMateriales( )
    {
        setText(MultiLingualConstants.tabMateriales_text( ));
        setSize( 800, 600 );
        
        LayoutContainer container = new LayoutContainer( );
        container.setLayout( new AbsoluteLayout( ) );

        gridMateriales = new Grid<MaterialModel>( new ListStore<MaterialModel>( ), getColumnModel() );     
        gridMateriales.setSize( "600px", "300px" );
        gridMateriales.setBorders( true );
        gridMateriales.setStripeRows( true );

        ContentPanel cpMateriales = new ContentPanel( );
        cpMateriales.setBodyBorder( false );
        cpMateriales.setHeading( MultiLingualConstants.tableMateriales_heading( ) );
        cpMateriales.setButtonAlign( HorizontalAlignment.CENTER );
        cpMateriales.setLayout( new FitLayout( ) );
        cpMateriales.setSize( 600, 300 );
        cpMateriales.add( gridMateriales );
        container.add( cpMateriales, new AbsoluteData( 100, 30 ) );

        btnAgregar = new Button( MultiLingualConstants.btnAgregar_text( ) );
        container.add( btnAgregar, new AbsoluteData( 375, 345 ) );
        
        add(container);
        
        inicializarDialogos( );
        
        eventoCargarTab( );
        eventoAgregarMaterial( );
        eventoEditarMaterial( );
    }   

    public void inicializarDialogos( )
    {
        dialogoAgregarMaterial = new DialogoAgregarMaterial( this );
        dialogoEditarMaterial = new DialogoEditarMaterial( this );
    }

    private void eventoCargarTab( )
    {
        this.addListener( Events.Select, new Listener<BaseEvent>( )
        {
            @Override
            public void handleEvent( BaseEvent be )
            {
                cargarMateriales( );               
            }
        } );
    }

    private void eventoAgregarMaterial( )
    {
        btnAgregar.addSelectionListener( new SelectionListener<ButtonEvent>( )
        {
            @Override
            public void componentSelected( ButtonEvent ce )
            {
                dialogoAgregarMaterial.show( );
            }
        } );
    }

    private void eventoEditarMaterial( )
    {
        gridMateriales.addListener( Events.CellDoubleClick, new Listener<GridEvent<MaterialModel>>( )
        {
            @Override
            public void handleEvent( GridEvent<MaterialModel> e )
            {
                MaterialModel model = e.getGrid( ).getSelectionModel( ).getSelectedItem( );
                dialogoEditarMaterial.cargarDatos( model );
                idMaterial = model.getId( );
            }
        } );
    }

    public void agregarMaterial( String nombre)
    {
        MaterialBO material = new MaterialBO( );
        material.setNombre( nombre );   
        int idCurso = Registry.get( "idCurso" );
        material.setIdCurso( idCurso );

        planificadorService.agregarMaterial( material, new AsyncCallback<Integer>( )
        {
            @Override
            public void onSuccess( Integer result )
            {
                cargarMateriales( );
                Info.display( "sami", Mensajero.mostrarMensaje( result ) );
            }

            @Override
            public void onFailure( Throwable caught )
            {
                Info.display( "Error", Mensajero.ON_FAILURE );
            }
        } );
    }

    public void editarMaterial( String nombre )
    {
        MaterialBO material = new MaterialBO( );
        material.setNombre( nombre );
        material.setId( idMaterial );

        planificadorService.editarMaterial( material, new AsyncCallback<Integer>( )
        {
            @Override
            public void onSuccess( Integer result )
            {
                cargarMateriales( );
                Info.display( "sami", Mensajero.mostrarMensaje( result ) );
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

    public void actualizarTablaMateriales( ListStore<MaterialModel> materiales )
    {
        gridMateriales.reconfigure( materiales, getColumnModel( ) );
    }
    
    private ColumnModel getColumnModel( )
    {
        List<ColumnConfig> configsMateriales = new ArrayList<ColumnConfig>( );

        ColumnConfig columnObj = new ColumnConfig( "nombre", MultiLingualConstants.columnObjEspecificos_nombre( ), 575);
        columnObj.setAlignment( HorizontalAlignment.LEFT);
        configsMateriales.add( columnObj );

        return new ColumnModel( configsMateriales );
    }
}
