package co.edu.unicesi.sami.competencias;

import javax.ejb.Remote;

import co.edu.unicesi.sami.bo.MetaTerminalBO;
import co.edu.unicesi.sami.bo.ObjetivoEspecificoBO;
import co.edu.unicesi.sami.bo.ObjetivoGeneralBO;
import co.edu.unicesi.sami.bo.ObjetivoTerminalBO;
import co.edu.unicesi.sami.bo.UnidadBO;

@Remote
public interface GestionCompetenciasRemote {
	public int agregarUnidad(UnidadBO unidad);
    public int editarUnidad(UnidadBO unidad);
    public UnidadBO buscarUnidad(int idUnidad);
    
    public int agregarObjGeneral(ObjetivoGeneralBO objGeneral);
    public int editarObjGeneral(ObjetivoGeneralBO objGeneral);
    public ObjetivoGeneralBO buscarObjGeneral(int idCurso);
    
    public int agregarObjTerminal(ObjetivoTerminalBO objTerminal);
    public int editarObjTerminal(ObjetivoTerminalBO objTerminal);
    public ObjetivoTerminalBO buscarObjTerminal(int idObjTerminal);
    
    public int agregarObjEspecifico(ObjetivoEspecificoBO objEspecifico);
    public int editarObjEspecifico(ObjetivoEspecificoBO objEspecifico);
    public ObjetivoEspecificoBO buscarObjEspecifico(int idObjEspecifico);
    
    public int agregarMetaTerminal(MetaTerminalBO metaTerminal);
    public int eliminarMetaTerminal(MetaTerminalBO metaTerminal);
    public MetaTerminalBO buscarMetaTerminal(int idMetaTerminal);   
}
