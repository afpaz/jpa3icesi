package co.edu.unicesi.sami.client.home;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicesi.sami.bo.CursoBO;
import co.edu.unicesi.sami.client.controller.DTEvent;
import co.edu.unicesi.sami.client.curso.CursoService;
import co.edu.unicesi.sami.client.curso.CursoServiceAsync;
import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;
import co.edu.unicesi.sami.client.model.CursoModel;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class PanelCursos extends LayoutContainer
{

    private final static MultiLingualConstants MultiLingualConstants = GWT.create( MultiLingualConstants.class );
    private final CursoServiceAsync cursoService = GWT.create( CursoService.class );

    private Grid<CursoModel> gridCursos;

    public PanelCursos( )
    {
        gridCursos = new Grid<CursoModel>( new ListStore<CursoModel>( ), getColumnModel( ) );
        gridCursos.setBorders( true );
        gridCursos.setStripeRows( true );

        ContentPanel cp = new ContentPanel( );
        cp.setBodyBorder( false );
        cp.setHeading( MultiLingualConstants.tableCursos_heading( ) );
        cp.setButtonAlign( HorizontalAlignment.CENTER );
        cp.setLayout( new FitLayout( ) );
        cp.setSize( 310, 600 );
        cp.add( gridCursos );

        add( cp );

        cargarCursos( );
        eventoSeleccionarCurso( );
    }

    private void cargarCursos( )
    {       
        cursoService.listarCursos( new AsyncCallback<List<CursoBO>>( )
        {            
            @Override
            public void onSuccess( List<CursoBO> result )
            {
                Dispatcher.forwardEvent( DTEvent.LISTAR_CURSOS, result );                
            }
            
            @Override
            public void onFailure( Throwable caught )
            {
                Info.display( "Error", Mensajero.ON_FAILURE);                
            }
        } );
    }

    private void eventoSeleccionarCurso( )
    {
        gridCursos.addListener( Events.CellClick, new Listener<GridEvent<CursoModel>>( )
        {
            public void handleEvent( GridEvent<CursoModel> e )
            {
                CursoModel cursoModel = e.getGrid( ).getSelectionModel( ).getSelectedItem( );
                Dispatcher.forwardEvent( DTEvent.SELECCIONAR_CURSO, cursoModel );
            }
        } );

    }

    public void actualizarPanel( ListStore<CursoModel> listaCursos)
    {
        gridCursos.reconfigure( listaCursos, getColumnModel( ) );
    }

    private ColumnModel getColumnModel( )
    {
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>( );

        ColumnConfig column = new ColumnConfig( "id", 10 );
        column.setHidden( true );
        configs.add( column );

        column = new ColumnConfig( "codigo", MultiLingualConstants.columnCursos_codigo( ), 50 );
        column.setAlignment( HorizontalAlignment.CENTER );
        configs.add( column );

        column = new ColumnConfig( "nombre", MultiLingualConstants.columnCursos_nombre( ), 250 );
        column.setAlignment( HorizontalAlignment.LEFT );
        configs.add( column );

        return new ColumnModel( configs );
    }

}
