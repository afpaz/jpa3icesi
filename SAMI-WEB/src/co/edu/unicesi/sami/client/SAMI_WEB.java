/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package co.edu.unicesi.sami.client;
import co.edu.unicesi.sami.client.controller.DTViewController;
import co.edu.unicesi.sami.client.home.PanelCursos;
import co.edu.unicesi.sami.client.home.TabMateriales;
import co.edu.unicesi.sami.client.home.TabObjEspecificos;
import co.edu.unicesi.sami.client.home.TabObjGeneral;
import co.edu.unicesi.sami.client.home.TabObjTerminales;
import co.edu.unicesi.sami.client.home.TabPlanificador;
import co.edu.unicesi.sami.client.home.TabRecursos;
import co.edu.unicesi.sami.client.home.TabSaberes;
import co.edu.unicesi.sami.client.home.TabUnidades;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SAMI_WEB implements EntryPoint
{
    /**
     * This is the entry point method.
     */
    public void onModuleLoad( )
    {
        Dispatcher.get( ).addController( new DTViewController( ) );

        TabPanel tab = new TabPanel( );
        tab.setSize( 800, 600 );
        tab.disable( );
        Registry.register( "tabs", tab );
        
        PanelCursos panelCursos = new PanelCursos( );
        Registry.register( "panelCursos", panelCursos );
        
        TabObjGeneral tabObjGeneral = new TabObjGeneral( );        
        Registry.register( "tabObjGeneral", tabObjGeneral );
        
        TabObjTerminales tabObjTerminales = new TabObjTerminales( );
        Registry.register( "tabObjTerminales", tabObjTerminales );
        
        TabUnidades tabUnidades = new TabUnidades( );
        Registry.register( "tabUnidades", tabUnidades );
        
        TabObjEspecificos tabObjEspecificos = new TabObjEspecificos( );
        Registry.register( "tabObjEspecificos", tabObjEspecificos );
        
        TabSaberes tabSaberes = new TabSaberes( );
        Registry.register( "tabSaberes", tabSaberes );
        
        TabMateriales tabMateriales = new TabMateriales( );
        Registry.register( "tabMateriales", tabMateriales );
        
        TabRecursos tabRecursos = new TabRecursos( );
        Registry.register( "tabRecursos", tabRecursos );
        
        TabPlanificador tabPlanificador = new TabPlanificador( );
        Registry.register( "tabPlanificador", tabPlanificador );

        tab.add( tabObjGeneral );
        tab.add( tabObjTerminales );
        tab.add( tabUnidades );
        tab.add( tabObjEspecificos );
        tab.add( tabSaberes );
        tab.add( tabMateriales );
        tab.add( tabRecursos );
        tab.add( tabPlanificador );
        RootPanel.get("listadoCursos").add( panelCursos );        
        RootPanel.get("tabs").add( tab );
    }
}
