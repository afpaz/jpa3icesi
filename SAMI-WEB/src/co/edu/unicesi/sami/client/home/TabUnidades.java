package co.edu.unicesi.sami.client.home;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicesi.sami.bo.ObjetivoTerminalBO;
import co.edu.unicesi.sami.bo.UnidadBO;
import co.edu.unicesi.sami.client.listados.ListadosService;
import co.edu.unicesi.sami.client.listados.ListadosServiceAsync;
import co.edu.unicesi.sami.client.model.CursoModel;
import co.edu.unicesi.sami.client.model.ObjetivoTerminalModel;
import co.edu.unicesi.sami.client.model.UnidadModel;
import co.edu.unicesi.sami.client.competencias.CompetenciasService;
import co.edu.unicesi.sami.client.competencias.CompetenciasServiceAsync;
import co.edu.unicesi.sami.client.controller.DTEvent;
import co.edu.unicesi.sami.client.home.dialogos.DialogoAgregarObjTerminal;
import co.edu.unicesi.sami.client.home.dialogos.DialogoAgregarUnidad;
import co.edu.unicesi.sami.client.home.dialogos.DialogoEditarObjTerminal;
import co.edu.unicesi.sami.client.home.dialogos.DialogoEditarUnidad;
import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;

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
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.DualListField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.CheckColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

public class TabUnidades extends TabItem {

	private final static MultiLingualConstants MultiLingualConstants = GWT
			.create(MultiLingualConstants.class);
	private final CompetenciasServiceAsync competenciasService = GWT
			.create(CompetenciasService.class);
	private final ListadosServiceAsync listadosService = GWT
			.create(ListadosService.class);

	private DialogoAgregarUnidad dialogoAgregarUnidad;
	private DialogoEditarUnidad dialogoEditarUnidad;

	private Button btnAgregar;
	private Button btnAgregarMetasTerminales;

	private Grid<UnidadModel> gridUnidad;
	private DualListField<ObjetivoTerminalModel> dualListObjTerminales;

	private int idUnidad;

	public TabUnidades() {
		setText(MultiLingualConstants.tabUnidades_text());
		setSize(800, 600);
		LayoutContainer container = new LayoutContainer();
		container.setLayout(new AbsoluteLayout());

		// Aqui se agrega la unidades
		ListStore<UnidadModel> listaUnidades = new ListStore<UnidadModel>();

		gridUnidad = new Grid<UnidadModel>(listaUnidades, getColumnModel());
		gridUnidad.setBorders(true);
		gridUnidad.setStripeRows(true);
		gridUnidad.setSize("600px", "150px");

		ContentPanel cpUnidad = new ContentPanel();
		cpUnidad.setBodyBorder(false);
		cpUnidad.setHeading(MultiLingualConstants.tableUnidades_heading());
		cpUnidad.setButtonAlign(HorizontalAlignment.CENTER);
		cpUnidad.setLayout(new FitLayout());
		cpUnidad.setSize(600, 150);
		cpUnidad.add(gridUnidad);
		container.add(cpUnidad, new AbsoluteData(100, 30));

		dualListObjTerminales = new DualListField<ObjetivoTerminalModel>();
		dualListObjTerminales.getFromList().setStore(
				new ListStore<ObjetivoTerminalModel>());
		dualListObjTerminales.getToList().setStore(
				new ListStore<ObjetivoTerminalModel>());
		dualListObjTerminales.setSize(600, 150);
		dualListObjTerminales.setFieldLabel(MultiLingualConstants
				.dlstfldNewDuallistfield_fieldLabel());

		ContentPanel cpObjetivoTerminal = new ContentPanel();
		cpObjetivoTerminal.setBodyBorder(false);
		cpObjetivoTerminal.setHeading(MultiLingualConstants
				.tableObjTerminales_heading());
		cpObjetivoTerminal.setButtonAlign(HorizontalAlignment.CENTER);
		cpObjetivoTerminal.setLayout(new FitLayout());
		cpObjetivoTerminal.setSize(600, 150);
		cpObjetivoTerminal.add(dualListObjTerminales);
		container.add(cpObjetivoTerminal, new AbsoluteData(100, 270));

		btnAgregar = new Button(MultiLingualConstants.btnAgregar_text());
		container.add(btnAgregar, new AbsoluteData(375, 210));

		btnAgregarMetasTerminales = new Button(
				MultiLingualConstants.btnAgregarMetasTerminales_text());
		container.add(btnAgregarMetasTerminales, new AbsoluteData(375, 450));

		add(container);

		inicializarDialogos();
		eventoCargarTab();
		eventoAgregarUnidad();
		eventoEditarUnidad();

	}

	public void inicializarDialogos() {
		dialogoAgregarUnidad = new DialogoAgregarUnidad(this);
		dialogoEditarUnidad = new DialogoEditarUnidad(this);
	}

	private void eventoCargarTab() {
		this.addListener(Events.Select, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				cargarUnidades();
				cargarObjetivosTerminales();
			}
		});
	}

	private void cargarUnidades() {
		int idCurso = Registry.get("idCurso");
		listadosService.listarUnidadesPorCurso(idCurso,
				new AsyncCallback<List<UnidadBO>>() {
					@Override
					public void onSuccess(List<UnidadBO> result) {
						Dispatcher
								.forwardEvent(DTEvent.LISTAR_UNIDADES, result);
					}

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", Mensajero.ON_FAILURE);
					}
				});
	}

	private void cargarObjetivosTerminales() {
		int idCurso = Registry.get("idCurso");
		listadosService.listarObjTerminalesPorCurso(idCurso,
				new AsyncCallback<List<ObjetivoTerminalBO>>() {
					@Override
					public void onSuccess(List<ObjetivoTerminalBO> result) {
						Dispatcher.forwardEvent(
								DTEvent.LISTAR_OBJ_TERMINALES_UNIDADES, result);
					}

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", Mensajero.ON_FAILURE);
					}
				});
	}

	private void cargarObjetivosTerminalesPorUnidad() {

	}

	private void eventoAgregarUnidad() {
		btnAgregar.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				dialogoAgregarUnidad.show();
			}
		});
	}

	private void eventoEditarUnidad() {
		gridUnidad.addListener(Events.CellDoubleClick,
				new Listener<GridEvent<UnidadModel>>() {
					@Override
					public void handleEvent(GridEvent<UnidadModel> e) {
						UnidadModel model = e.getGrid().getSelectionModel()
								.getSelectedItem();
						idUnidad = model.getId();
						dialogoEditarUnidad.cargarDatos(model);
						dialogoEditarUnidad.show();
					}
				});
	}

	private void eventoAgregarMetasTerminales() {
		btnAgregarMetasTerminales
				.addSelectionListener(new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						//Debe de comparar los valores de las listas de la duallist y agregar de acuerdo al caso

					}
				});
	}

	public void agregarUnidad(String nombre, int numero, String contenido) {
		UnidadBO unidad = new UnidadBO();
		unidad.setNombre(nombre);
		unidad.setContenido(contenido);
		unidad.setNumero(numero);
		int idCurso = Registry.get("idCurso");
		unidad.setIdCurso(idCurso);

		competenciasService.agregarUnidad(unidad, new AsyncCallback<Integer>() {
			@Override
			public void onSuccess(Integer result) {
				cargarUnidades();
				Info.display("sami", Mensajero.mostrarMensaje(result));
			}

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Error", Mensajero.ON_FAILURE);
			}
		});
	}

	public void editarUnidad(String nombre, int numero, String contenido) {
		UnidadBO unidad = new UnidadBO();
		unidad.setNombre(nombre);
		unidad.setContenido(contenido);
		unidad.setId(idUnidad);

		competenciasService.editarUnidad(unidad, new AsyncCallback<Integer>() {
			@Override
			public void onSuccess(Integer result) {
				cargarUnidades();
				Info.display("sami", Mensajero.mostrarMensaje(result));
			}

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Error", Mensajero.ON_FAILURE);
			}
		});
	}

	public void actualizarTablaUnidades(ListStore<UnidadModel> objModel) {
		// TODO Auto-generated method stub
		this.gridUnidad.reconfigure(objModel, getColumnModel());
	}

	private ColumnModel getColumnModel() {
		List<ColumnConfig> configsUnidades = new ArrayList<ColumnConfig>();

		ColumnConfig columnObj = new ColumnConfig("numero",
				MultiLingualConstants.columnUnidades_numero(), 50);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsUnidades.add(columnObj);

		columnObj = new ColumnConfig("nombre",
				MultiLingualConstants.columnUnidades_nombre(), 100);
		columnObj.setAlignment(HorizontalAlignment.CENTER);
		configsUnidades.add(columnObj);

		columnObj = new ColumnConfig("contenido",
				MultiLingualConstants.columnUnidades_contenido(), 450);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsUnidades.add(columnObj);

		return new ColumnModel(configsUnidades);
	}

	public void actualizarListaObjTerminales(
			ListStore<ObjetivoTerminalModel> objModel) {
		// TODO Auto-generated method stub
		dualListObjTerminales.getFromList().getStore().removeAll();
		dualListObjTerminales.getFromList().getStore()
				.add(objModel.getModels());
	}

}
