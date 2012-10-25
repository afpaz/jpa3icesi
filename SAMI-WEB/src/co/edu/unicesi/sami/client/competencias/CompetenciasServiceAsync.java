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
package co.edu.unicesi.sami.client.competencias;

import co.edu.unicesi.sami.bo.MetaTerminalBO;
import co.edu.unicesi.sami.bo.ObjetivoEspecificoBO;
import co.edu.unicesi.sami.bo.ObjetivoGeneralBO;
import co.edu.unicesi.sami.bo.ObjetivoTerminalBO;
import co.edu.unicesi.sami.bo.SesionBO;
import co.edu.unicesi.sami.bo.UnidadBO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CompetenciasServiceAsync
{
    public void agregarUnidad( UnidadBO unidad, AsyncCallback<Integer> callback );
    public void editarUnidad( UnidadBO unidad, AsyncCallback<Integer> callback );
    public void buscarUnidad( int idUnidad, AsyncCallback<UnidadBO> callback );

    public void agregarObjGeneral( ObjetivoGeneralBO objGeneral, AsyncCallback<Integer> callback );
    public void editarObjGeneral( ObjetivoGeneralBO objGeneral, AsyncCallback<Integer> callback );
    public void buscarObjGeneral( int idCurso, AsyncCallback<ObjetivoGeneralBO> callback );

    public void agregarObjTerminal( ObjetivoTerminalBO objTerminal, AsyncCallback<Integer> callback );
    public void editarObjTerminal( ObjetivoTerminalBO objTerminal, AsyncCallback<Integer> callback );
    public void buscarObjTerminal( int idObjTerminal, AsyncCallback<ObjetivoTerminalBO> callback );

    public void agregarObjEspecifico( ObjetivoEspecificoBO objEspecifico, AsyncCallback<Integer> callback );
    public void editarObjEspecifico( ObjetivoEspecificoBO objEspecifico, AsyncCallback<Integer> callback );
    public void buscarObjEspecifico( int idObjEspecifico, AsyncCallback<ObjetivoEspecificoBO> callback );

    public void agregarMetaTerminal( MetaTerminalBO metaTerminal, AsyncCallback<Integer> callback );
    public void eliminarMetaTerminal( MetaTerminalBO metaTerminal, AsyncCallback<Integer> callback );
    public void buscarMetaTerminal( int idMetaTerminal, AsyncCallback<MetaTerminalBO> callback );

}
