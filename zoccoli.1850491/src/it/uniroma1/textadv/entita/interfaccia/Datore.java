package it.uniroma1.textadv.entita.interfaccia;

import it.uniroma1.textadv.entita.personaggio.Personaggio;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

/**
 * Interfaccia che accomuna tutte le entita che hanno un inventario, e quindi possono dare un oggetto
 * @author gioele
 *
 */
public interface Datore {
	/**
	 * Metodo che da l'oggetto richiesto se presente
	 * @param nomeInventario
	 * @return
	 * @throws AzioneException
	 */
	Inventario dai(String nomeInventario) throws AzioneException;
	
	/**
	 * Metodo che da l'oggetto richiesto ad un determinato personaggio
	 * @param nomeInventario
	 * @param p
	 * @throws AzioneException
	 */
	default void daiA(String nomeInventario, Personaggio p) throws AzioneException{
		p.prendi(dai(nomeInventario));
	};
}
