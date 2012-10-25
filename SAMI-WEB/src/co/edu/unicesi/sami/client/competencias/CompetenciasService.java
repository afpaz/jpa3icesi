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

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("CompetenciasService")
public interface CompetenciasService extends RemoteService
{
    public int agregarUnidad( UnidadBO unidad );
    public int editarUnidad( UnidadBO unidad );
    public UnidadBO buscarUnidad( int idUnidad );

    public int agregarObjGeneral( ObjetivoGeneralBO objGeneral );
    public int editarObjGeneral( ObjetivoGeneralBO objGeneral );
    public ObjetivoGeneralBO buscarObjGeneral( int idCurso );

    public int agregarObjTerminal( ObjetivoTerminalBO objTerminal );
    public int editarObjTerminal( ObjetivoTerminalBO objTerminal );
    public ObjetivoTerminalBO buscarObjTerminal( int idObjTerminal );

    public int agregarObjEspecifico( ObjetivoEspecificoBO objEspecifico );
    public int editarObjEspecifico( ObjetivoEspecificoBO objEspecifico );
    public ObjetivoEspecificoBO buscarObjEspecifico( int idObjEspecifico );

    public int agregarMetaTerminal( MetaTerminalBO metaTerminal );
    public int eliminarMetaTerminal( MetaTerminalBO metaTerminal );
    public MetaTerminalBO buscarMetaTerminal( int idMetaTerminal );

}
