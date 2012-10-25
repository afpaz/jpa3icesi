package co.edu.unicesi.sami.client.home;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicesi.sami.bo.ObjetivoGeneralBO;
import co.edu.unicesi.sami.bo.ObjetivoTerminalBO;
import co.edu.unicesi.sami.client.home.Mensajero;
import co.edu.unicesi.sami.client.competencias.CompetenciasService;
import co.edu.unicesi.sami.client.competencias.CompetenciasServiceAsync;
import co.edu.unicesi.sami.client.controller.DTEvent;
import co.edu.unicesi.sami.client.home.dialogos.DialogoAgregarObjTerminal;
import co.edu.unicesi.sami.client.home.dialogos.DialogoEditarObjTerminal;
import co.edu.unicesi.sami.client.listados.ListadosService;
import co.edu.unicesi.sami.client.listados.ListadosServiceAsync;
import co.edu.unicesi.sami.client.model.CursoModel;
import co.edu.unicesi.sami.client.model.MetaTerminalModel;
import co.edu.unicesi.sami.client.model.ObjetivoTerminalModel;
import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.EventType;
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
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TabObjTerminales extends TabItem {
	private final static MultiLingualConstants MultiLingualConstants = GWT
			.create(MultiLingualConstants.class);
	private final CompetenciasServiceAsync competenciasService = GWT
			.create(CompetenciasService.class);
	private final ListadosServiceAsync listadosService = GWT
			.create(ListadosService.class);

	private int idObjTerminal;

	private DialogoAgregarObjTerminal dialogoAgregarObjTerminal;
	private DialogoEditarObjTerminal dialogoEditarObjTerminal;

	private Text labObjGeneral;
	private TextArea txtObjGeneral;
	private Button btnAgregar;

	private Grid<ObjetivoTerminalModel> gridObjTerminales;

	public TabObjTerminales() {
		setText(MultiLingualConstants.tabObjTerminales_text());
		setSize(800, 600);
		LayoutContainer container = new LayoutContainer();
		container.setLayout(new AbsoluteLayout());

		labObjGeneral = new Text(MultiLingualConstants.labObjGeneral_text());
		container.add(labObjGeneral, new AbsoluteData(100, 30));

		txtObjGeneral = new TextArea();
		txtObjGeneral.setSize("600px", "150px");
		container.add(txtObjGeneral, new AbsoluteData(100, 50));

		btnAgregar = new Button(MultiLingualConstants.btnAgregar_text());
		container.add(btnAgregar, new AbsoluteData(375, 510));

		gridObjTerminales = new Grid<ObjetivoTerminalModel>(
				new ListStore<ObjetivoTerminalModel>(), getColumnModel());
		gridObjTerminales.setBorders(true);
		gridObjTerminales.setStripeRows(true);
		gridObjTerminales.setSize("600px", "275px");

		ContentPanel cpObjs = new ContentPanel();
		cpObjs.setBodyBorder(false);
		cpObjs.setHeading(MultiLingualConstants.tableObjTerminales_heading());
		cpObjs.setButtonAlign(HorizontalAlignment.CENTER);
		cpObjs.setLayout(new FitLayout());
		cpObjs.setSize(600, 275);
		cpObjs.add(gridObjTerminales);
		container.add(cpObjs, new AbsoluteData(100, 225));

		add(container);

		inicializarDialogos();

		eventoCargarTab();
		eventoAgregarObjTerminal();
		eventoEditarObjTerminal();
	}

	public void inicializarDialogos() {
		dialogoAgregarObjTerminal = new DialogoAgregarObjTerminal(this);
		dialogoEditarObjTerminal = new DialogoEditarObjTerminal(this);
	}

	private void eventoCargarTab() {
		this.addListener(Events.Select, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				cargarObjGeneral();
				cargarObjTerminales();
			}
		});
	}

	private void eventoAgregarObjTerminal() {
		btnAgregar.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				dialogoAgregarObjTerminal.show();
			}
		});
	}

	private void eventoEditarObjTerminal() {
		gridObjTerminales.addListener(Events.CellDoubleClick,
				new Listener<GridEvent<ObjetivoTerminalModel>>() {
					@Override
					public void handleEvent(GridEvent<ObjetivoTerminalModel> e) {
						ObjetivoTerminalModel model = e.getGrid()
								.getSelectionModel().getSelectedItem();
						dialogoEditarObjTerminal.cargarDatos(model);
						idObjTerminal = model.getId();
						dialogoEditarObjTerminal.show();
					}
				});
	}

	public void agregarObjTerminal(String nombre, String contenido) {
		ObjetivoTerminalBO objTerminal = new ObjetivoTerminalBO();
		objTerminal.setNombre(nombre);
		objTerminal.setContenido(contenido);
		int idCurso = Registry.get("idCurso");
		objTerminal.setIdCurso(idCurso);

		competenciasService.agregarObjTerminal(objTerminal,
				new AsyncCallback<Integer>() {
					@Override
					public void onSuccess(Integer result) {
						cargarObjTerminales();
						Info.display("sami", Mensajero.mostrarMensaje(result));
					}

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", Mensajero.ON_FAILURE);
					}
				});
	}

	public void editarObjTerminal(String nombre, String contenido) {
		ObjetivoTerminalBO objTerminal = new ObjetivoTerminalBO();
		objTerminal.setNombre(nombre);
		objTerminal.setContenido(contenido);
		objTerminal.setId(idObjTerminal);

		competenciasService.editarObjTerminal(objTerminal,
				new AsyncCallback<Integer>() {
					@Override
					public void onSuccess(Integer result) {
						cargarObjTerminales();
						Info.display("sami", Mensajero.mostrarMensaje(result));
					}

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", Mensajero.ON_FAILURE);
					}
				});
	}

	private void cargarObjGeneral() {
		int idCurso = Registry.get("idCurso");
		competenciasService.buscarObjGeneral(idCurso,
				new AsyncCallback<ObjetivoGeneralBO>() {
					@Override
					public void onSuccess(ObjetivoGeneralBO objGeneral) {
						if (objGeneral != null) {
							txtObjGeneral.setValue(objGeneral.getContenido());
						} else {
							txtObjGeneral.clear();
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", Mensajero.ON_FAILURE);
					}

				});

	}

	private void cargarObjTerminales() {
		int idCurso = Registry.get("idCurso");
		listadosService.listarObjTerminalesPorCurso(idCurso,
				new AsyncCallback<List<ObjetivoTerminalBO>>() {
					@Override
					public void onSuccess(List<ObjetivoTerminalBO> result) {
						Dispatcher.forwardEvent(DTEvent.LISTAR_OBJ_TERMINALES,
								result);
					}

					@Override
					public void onFailure(Throwable caught) {
						Info.display("Error", Mensajero.ON_FAILURE);
					}
				});
	}

	public void actualizarTablaObjTerminales(
			ListStore<ObjetivoTerminalModel> objTerminales) {
		gridObjTerminales.reconfigure(objTerminales, getColumnModel());
	}

	private ColumnModel getColumnModel() {
		List<ColumnConfig> configsObjTerminales = new ArrayList<ColumnConfig>();

		ColumnConfig columnObj = new ColumnConfig("nombre",
				MultiLingualConstants.columnObjEspecificos_nombre(), 50);
		columnObj.setAlignment(HorizontalAlignment.CENTER);
		configsObjTerminales.add(columnObj);

		columnObj = new ColumnConfig("contenido",
				MultiLingualConstants.columnObjEspecificos_contenido(), 525);
		columnObj.setAlignment(HorizontalAlignment.LEFT);
		configsObjTerminales.add(columnObj);

		return new ColumnModel(configsObjTerminales);
	}
}
