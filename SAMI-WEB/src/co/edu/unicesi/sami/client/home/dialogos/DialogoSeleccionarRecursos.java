package co.edu.unicesi.sami.client.home.dialogos;

import java.util.List;

import co.edu.unicesi.sami.client.internationalization.MultiLingualConstants;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.google.gwt.core.client.GWT;

public class DialogoSeleccionarRecursos extends Dialog {

	private final static MultiLingualConstants MultiLingualConstants = GWT
			.create(MultiLingualConstants.class);

	private ListView listRecursos;
	private DialogoAgregarSesion dialogoAgregarSesion;
	private DialogoEditarSesion dialogoEditarSesion;
	private boolean agregarSesion;
	private boolean editarSesion;

	public DialogoSeleccionarRecursos(DialogoAgregarSesion dialogoAgregarSesion) {
		this.dialogoAgregarSesion=dialogoAgregarSesion;
		agregarSesion=true;
		editarSesion=false;
		inicializar();
	}
	
	public DialogoSeleccionarRecursos(DialogoEditarSesion dialogoEditarSesion) {
		this.dialogoEditarSesion=dialogoEditarSesion;
		agregarSesion=false;
		editarSesion=true;
		inicializar();
	}
	
	public void inicializar(){
		setModal(true);
		setHeading(MultiLingualConstants.dialogoSeleccionarRecurso_heading());
		setLayout(new AbsoluteLayout());

		listRecursos = new ListView(new ListStore());
		add(listRecursos, new AbsoluteData(68, 6));
		listRecursos.setSize("294px", "188px");

		eventoAdjuntarRecurso();
		eventoCerrarVentana();
	}

	private void eventoAdjuntarRecurso() {
		getButtonById(OK).addSelectionListener(
				new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						adjuntarRecurso();
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

	public void cargarDatos() {

	}

	private void adjuntarRecurso() {
		if(listRecursos.getSelectOnOver()){			
			List seleccionados = listRecursos.getSelectionModel().getSelectedItems();
			if(agregarSesion){
				dialogoAgregarSesion.adjuntarRecursos(seleccionados);
			}else{
				dialogoEditarSesion.adjuntarRecursos(seleccionados);
			}
		}
	}

	private void limpiarDatos() {
		listRecursos.getElements().clear();
		hide();
	}
}
