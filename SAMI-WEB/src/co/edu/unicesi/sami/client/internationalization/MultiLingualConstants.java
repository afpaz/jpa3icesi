package co.edu.unicesi.sami.client.internationalization;

import com.google.gwt.i18n.client.Constants;

public interface MultiLingualConstants extends Constants
{
    // Mensajes
    String onFailure( );
    String msgAgregar( );
    String msgEditar( );
    String msgEliminar( );
    String msgError( );

    // PanelCursos
    String tableCursos_heading( );
    String columnCursos_nombre( );
    String columnCursos_codigo( );

    // Botones
    String btnAgregar_text( );
    String btnEditar_text( );
    String btnAsociar_text();
    String btnSeleccionarRecurso_text();
    String btnVerTodos_text();
    
    // Labels Objetivos
    String labNombre_text( );
    String labContenido_text( );
    String labTipo_text( );
    String labNumero_text();

    // TabObjGeneral
    String tabObjGeneral_text( );
    String labObjGeneral_text( );

    // TabObjTerminal
    String tabObjTerminales_text( );

    String tableObjTerminales_heading( );
    String columnObjTerminales_nombre( );
    String columnObjTerminales_contenido( );
    
    // TabUnidades
    String tabUnidades_text();
    String tableUnidades_heading();
    String columnUnidades_nombre( );
    String columnUnidades_numero( );
    String columnUnidades_contenido( );
    String btnAgregarMetasTerminales_text();

    // TabObjEspeficicos
    String tabObjEspecificos_text( );

    String tableMetasTerminales_heading( );
    String columnMetasTerminales_nombreUnidad( );
    String columnMetasTerminales_contenidoUnidad( );
    String columnMetasTerminales_nombreObjTerminal( );
    String columnMetasTerminales_contenidoObjTerminal( );

    String tableObjEspecificos_heading( );
    String columnObjEspecificos_nombre( );
    String columnObjEspecificos_contenido( );

    String msgSeleccionarMetaTerminal( );
    String msgMetaTerminal( );

    // TabSaberes
    String tabSaberes_text( );

    String tableSaberes_heading( );
    String columnSaberes_nombre( );
    String columnSaberes_tipo( );
    String columnSaberes_contenido( );

    String msgSeleccionarObjEspecifico( );
    String msgObjEspecifico( );

    // TabMateriales
    String tabMateriales_text( );
    String tableMateriales_heading( );

    // TabRecursos
    String tabRecursos_text( );
    String tableRecursos_heading( );

    String msgSeleccionarSaber( );
    String msgRecurso( );

    String msgSeleccionarMaterial( );
    
    // TabPlanificador
    String tabPlanificador_text( );
    String tablePlanificador_heading( );
    String dualListUnidadesSeleccionar_text();
    String dualListUnidadesSeleccionadas_text();
    String listObjEspecificos_text();
    String tableSesiones_heading();

    // DialogoAgregarObjTerminal
    String dialogoAgregarObjTerminal_heading( );
    // DialogoEditarObjTerminal
    String dialogoEditarObjTerminal_heading( );

    // DialogoAgregarObjEspecifico
    String dialogoAgregarObjEspecifico_heading( );
    // DialogoEditarObjEspecifico
    String dialogoEditarObjEspecifico_heading( );

    // DialogoAgregarSaber
    String dialogoAgregarSaber_heading( );
    // DialogoEditarSaber
    String dialogoEditarSaber_heading( );

    // DialogoAgregarMaterial
    String dialogoAgregarMaterial_heading( );
    // DialogoEditarMaterial
    String dialogoEditarMaterial_heading( );
    
    // DialogoAgregarUnidad
    String dialogoAgregarUnidad_heading() ;
    
    // DialogoEditarUnidad
    String dialogoEditarUnidad_heading();
    
    // DialogoAgregarSesion
    String dialogoAgregarSesion_heading() ;
    
    // DialogoEditarSesian
    String dialogoEditarSesion_heading() ;
    
    //DialogoSeleccionarRecurso
    String dialogoSeleccionarRecurso_heading();
	String dlstfldNewDuallistfield_fieldLabel();
}
