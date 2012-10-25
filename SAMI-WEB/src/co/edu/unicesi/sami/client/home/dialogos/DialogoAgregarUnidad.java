package co.edu.unicesi.sami.client.home.dialogos;

import co.edu.unicesi.sami.client.home.TabUnidades;
import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.google.gwt.core.client.GWT;

public class DialogoAgregarUnidad extends Dialog {

	private final static MultiLingualConstants MultiLingualConstants = GWT
			.create(MultiLingualConstants.class);

	private Text labNombre;
	private Text labContenido;
	private Text labNumero;
	private TextField<String> txtNombre;
	private TextField<String> txtNumero;
	private TextArea txtContenido;
	private TabUnidades tabUnidades;

	public DialogoAgregarUnidad(TabUnidades tabUnidades) {
		setModal(true);
		setHeading(MultiLingualConstants.dialogoAgregarUnidad_heading());
		setLayout(new AbsoluteLayout());
        setSize( 500, 300 );
		this.tabUnidades = tabUnidades;

		txtNombre = new TextField<String>();
		add(txtNombre, new AbsoluteData(157, 25));
		txtNombre.setSize("212px", "24px");

		txtNumero = new TextField<String>();
		add(txtNumero, new AbsoluteData(157, 68));
		txtNumero.setSize("212px", "24px");
		
		txtContenido = new TextArea();
		add(txtContenido, new AbsoluteData(157, 110));
		txtContenido.setSize("212px", "60px");

		labNombre = new Text(MultiLingualConstants.labNombre_text());
		add(labNombre, new AbsoluteData(30, 36));

		labContenido = new Text(MultiLingualConstants.labContenido_text());
		add(labContenido, new AbsoluteData(30, 110));

		labNumero = new Text(MultiLingualConstants.labNumero_text());
		add(labNumero, new AbsoluteData(30, 79));

		

		eventoAgregarUnidad();
		eventoCerrarVentana();
	}

	private void eventoAgregarUnidad() {
		getButtonById(OK).addSelectionListener(
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						agregarUnidad();
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

	private void agregarUnidad() {
		String nombre = txtNombre.getValue();
		String contenido = txtContenido.getValue();
		int numero = Integer.parseInt(txtNumero.getValue());
		tabUnidades.agregarUnidad(nombre, numero, contenido);
	}

	private void limpiarDatos() {
		txtNombre.clear();
		txtContenido.clear();
		txtNumero.clear();
		hide();
	}
}
