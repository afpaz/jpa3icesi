package co.edu.unicesi.sami.client.home;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicesi.sami.bo.ObjetivoTerminalBO;
import co.edu.unicesi.sami.bo.SesionBO;
import co.edu.unicesi.sami.client.competencias.CompetenciasService;
import co.edu.unicesi.sami.client.competencias.CompetenciasServiceAsync;
import co.edu.unicesi.sami.client.home.dialogos.DialogoAgregarObjTerminal;
import co.edu.unicesi.sami.client.home.dialogos.DialogoAgregarSesion;
import co.edu.unicesi.sami.client.home.dialogos.DialogoEditarObjTerminal;
import co.edu.unicesi.sami.client.home.dialogos.DialogoEditarSesion;
import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;
import co.edu.unicesi.sami.client.listados.ListadosService;
import co.edu.unicesi.sami.client.listados.ListadosServiceAsync;
import co.edu.unicesi.sami.client.model.ObjetivoEspecificoModel;
import co.edu.unicesi.sami.client.model.ObjetivoTerminalModel;
import co.edu.unicesi.sami.client.model.SesionModel;
import co.edu.unicesi.sami.client.model.UnidadModel;
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
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DualListField;
import com.extjs.gxt.ui.client.widget.form.ListField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TabPlanificador extends TabItem {
	private final static MultiLingualConstants MultiLingualConstants = GWT
			.create(MultiLingualConstants.class);
	private final PlanificadorServiceAsync planificadorService = GWT
			.create(PlanificadorService.class);

	private Text labUnidadesSeleccionar;
	private Text labUnidadesSeleccionadas;
	private Text labObjEspecificos;
	private DualListField dualListUnidadesSeleccionar;
	private Grid gridObjEspecificos;
	private Grid gridSesiones;
	private Button btnAgregar;
	private Button btnVerTodos;
	private DialogoAgregarSesion dialogoAgregarSesion;
	private DialogoEditarSesion dialogoEditarSesion;
	private int idSesion;

	public TabPlanificador() {

		setText(MultiLingualConstants.tabPlanificador_text());
		setSize(800, 600);
		LayoutContainer container = new LayoutContainer();
		container.setLayout(new AbsoluteLayout());

		labUnidadesSeleccionar = new Text(
				MultiLingualConstants.dualListUnidadesSeleccionar_text());
		container.add(labUnidadesSeleccionar, new AbsoluteData(100, 30));
		labUnidadesSeleccionadas = new Text(
				MultiLingualConstants.dualListUnidadesSeleccionadas_text());
		container.add(labUnidadesSeleccionadas, new AbsoluteData(450, 30));

		dualListUnidadesSeleccionar = new DualListField();
		dualListUnidadesSeleccionar.getFromList().setStore(
				new ListStore<UnidadModel>());
		dualListUnidadesSeleccionar.getToList().setStore(
				new ListStore<UnidadModel>());
		dualListUnidadesSeleccionar.setSize(600, 170);
		dualListUnidadesSeleccionar.setFieldLabel(MultiLingualConstants
				.dlstfldNewDuallistfield_fieldLabel());
		container.add(dualListUnidadesSeleccionar, new AbsoluteData(100, 50));

		gridObjEspecificos = new Grid<ObjetivoEspecificoModel>(
				new ListStore<ObjetivoEspecificoModel>(),
				getColumnModelObjEspecificos());
		gridObjEspecificos.setBorders(true);
		gridObjEspecificos.setStripeRows(true);
		gridObjEspecificos.setSize(200, 125);

		ContentPanel cpObjs = new ContentPanel();
		cpObjs.setBodyBorder(false);
		cpObjs.setHeading(MultiLingualConstants.tableObjEspecificos_heading());
		cpObjs.setButtonAlign(HorizontalAlignment.CENTER);
		cpObjs.setLayout(new FitLayout());
		cpObjs.setSize(200, 125);
		cpObjs.add(gridObjEspecificos);
		container.add(cpObjs, new AbsoluteData(300, 250));

		gridSesiones = new Grid<SesionModel>(new ListStore<SesionModel>(),
				getColumnModelSesiones());
		gridSesiones.setBorders(true);
		gridSesiones.setStripeRows(true);
		gridSesiones.setSize("600px", "125px");

		ContentPanel cpSes = new ContentPanel();
		cpSes.setBodyBorder(false);
		cpSes.setHeading(MultiLingualConstants.tableSesiones_heading());
		cpSes.setButtonAlign(HorizontalAlignment.CENTER);
		cpSes.setLayout(new FitLayout());
		cpSes.setSize(600, 125);
		cpSes.add(gridSesiones);
		container.add(cpSes, new AbsoluteData(100, 400));

		btnAgregar = new Button(MultiLingualConstants.btnAgregar_text());
		container.add(btnAgregar, new AbsoluteData(400, 550));

		btnAgregar = new Button(MultiLingualConstants.btnVerTodos_text());
		container.add(btnAgregar, new AbsoluteData(325, 550));

		add(container);
		inicializarDialogos();
		eventoCargarTab();
		eventoAgregarSesion();
		eventoEditarSesion();
	}

	public void inicializarDialogos() {
		dialogoAgregarSesion = new DialogoAgregarSesion(this);
		dialogoEditarSesion = new DialogoEditarSesion(this);
	}

	private void eventoCargarTab() {
		this.addListener(Events.Select, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				cargarUnidadesDisponibles();
				cargarObjetivosEspecificos();
			}
		});
	}

	private void eventoAgregarSesion() {
		btnAgregar.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				dialogoAgregarSesion.show();
			}
		});
	}

	private void eventoEditarSesion() {
		gridSesiones.addListener(Events.CellDoubleClick,
				new Listener<GridEvent<SesionModel>>() {
					@Override
					public void handleEvent(GridEvent<SesionModel> e) {
						SesionModel model = e.getGrid().getSelectionModel()
								.getSelectedItem();
						dialogoEditarSesion.cargarDatos(model);
						idSesion = model.getId();
					}
				});
	}

	public void agregarSesion(String nombre, int numero) {
		SesionBO sesion = new SesionBO();
		sesion.setNombre(nombre);
		int idCurso = Registry.get("idCurso");

		planificadorService.agregarSesion(sesion, new AsyncCallback<Integer>() {
			@Override
			public void onSuccess(Integer result) {
				cargarSesiones();
				Info.display("sami", Mensajero.mostrarMensaje(result));
			}

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Error", Mensajero.ON_FAILURE);
			}
		});
	}

	private void cargarUnidadesDisponibles() {
		// TODO Auto-generated method stub

	}

	private void cargarObjetivosEspecificos() {
		// TODO Auto-generated method stub

	}

	private void cargarSesiones() {

	}

	private ColumnModel getColumnModelObjEspecificos() {
		List<ColumnConfig> configsObj = new ArrayList<ColumnConfig>();

		ColumnConfig columnObj = new ColumnConfig("nombre",
				MultiLingualConstants.columnObjEspecificos_nombre(), 50);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsObj.add(columnObj);

		columnObj = new ColumnConfig("contenido",
				MultiLingualConstants.columnObjEspecificos_contenido(), 200);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsObj.add(columnObj);

		return new ColumnModel(configsObj);
	}

	private ColumnModel getColumnModelSesiones() {
		List<ColumnConfig> configsSesiones = new ArrayList<ColumnConfig>();

		ColumnConfig columnObj = new ColumnConfig("numero",
				MultiLingualConstants.columnObjEspecificos_nombre(), 50);
		columnObj.setAlignment(HorizontalAlignment.CENTER);
		configsSesiones.add(columnObj);

		columnObj = new ColumnConfig("tipo",
				MultiLingualConstants.columnObjEspecificos_contenido(), 50);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsSesiones.add(columnObj);

		columnObj = new ColumnConfig("encargado",
				MultiLingualConstants.columnObjEspecificos_contenido(), 100);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsSesiones.add(columnObj);

		columnObj = new ColumnConfig("descripcion",
				MultiLingualConstants.columnObjEspecificos_contenido(), 200);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsSesiones.add(columnObj);

		columnObj = new ColumnConfig("saberes",
				MultiLingualConstants.columnObjEspecificos_contenido(), 100);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsSesiones.add(columnObj);

		columnObj = new ColumnConfig("materiales",
				MultiLingualConstants.columnObjEspecificos_contenido(), 100);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsSesiones.add(columnObj);

		return new ColumnModel(configsSesiones);
	}
}