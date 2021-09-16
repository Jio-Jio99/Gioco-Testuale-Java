package it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto;

import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

/**
 * Eccezione lanciata in caso in cui l'entita non sia presente nell'inventario
 * @author gioele
 *
 */
public class OggettoNonInInventarioException extends AzioneException {
	public static final String ERRORE = "L'oggetto richiesto non è nell'inventario!";
	
	public OggettoNonInInventarioException() {
		super(ERRORE);
	}
}
