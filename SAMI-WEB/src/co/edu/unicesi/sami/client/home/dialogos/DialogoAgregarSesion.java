package co.edu.unicesi.sami.client.home.dialogos;

import java.util.List;

import co.edu.unicesi.sami.client.home.TabPlanificador;
import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.google.gwt.core.client.GWT;

public class DialogoAgregarSesion extends Dialog {

	private final static MultiLingualConstants MultiLingualConstants = GWT
			.create(MultiLingualConstants.class);

	private Text labNombre;
	private Text labNumero;
	private TextField<String> txtNombre;
	private TextField<Integer> txtNumero;
	private Button btnSeleccionarRecurso;
	private DialogoSeleccionarRecursos dialogoSeleccionarRecursos;
	private List listRecursos;

	public DialogoAgregarSesion(TabPlanificador tabPlanificador) {
		setModal(true);
		setHeading(MultiLingualConstants.dialogoAgregarSesion_heading());
		setLayout(new AbsoluteLayout());

		txtNombre = new TextField();
		add(txtNombre, new AbsoluteData(157, 25));
		txtNombre.setSize("212px", "24px");

		labNombre = new Text(MultiLingualConstants.labNombre_text());
		add(labNombre, new AbsoluteData(30, 36));

		labNumero = new Text(MultiLingualConstants.labNumero_text());
		add(labNumero, new AbsoluteData(30, 79));

		txtNumero = new TextField();
		add(txtNumero, new AbsoluteData(157, 68));
		txtNumero.setSize("212px", "24px");

		btnSeleccionarRecurso = new Button(
				MultiLingualConstants.dialogoSeleccionarRecurso_heading());
		add(btnSeleccionarRecurso, new AbsoluteData(200, 142));

		dialogoSeleccionarRecursos = new DialogoSeleccionarRecursos(this);

		eventoAgregarSesion();
		eventoCerrarVentana();
		eventoSeleccionarRecurso();
	}

	private void eventoSeleccionarRecurso() {
		btnSeleccionarRecurso
				.addSelectionListener(new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						// Debe de acomodar los recursos para el dialogo con el
						// metodo cargarDatos
						dialogoSeleccionarRecursos.show();
					}
				});
	}

	private void eventoAgregarSesion() {
		getButtonById(OK).addSelectionListener(
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						agregarSesion();
						limpiarDatos();
					}
				});
	}

	private void eventoCerrarVentana() {
		this.addListener(Events.Close, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				limpiarDatos();
			}
		});
	}

	private void agregarSesion() {
		String nombre = txtNombre.getValue();
		int numero = txtNumero.getValue();
	}

	private void limpiarDatos() {
		txtNombre.clear();
		txtNumero.clear();
		hide();
	}

	public void adjuntarRecursos(List recursosSeleccionados) {
		this.listRecursos = recursosSeleccionados;

	}
}
