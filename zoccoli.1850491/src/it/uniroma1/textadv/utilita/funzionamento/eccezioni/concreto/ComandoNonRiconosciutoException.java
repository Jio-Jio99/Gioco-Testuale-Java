package it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto;

import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;


/**
 * Eccezione lanciata in caso in cui il comando non sia presente tra quelli realizzati e/o sia scritto in modo scorretto, e quindi non è riconosciuto
 * @author gioele
 *
 */
public class ComandoNonRiconosciutoException extends AzioneException{
	public static final String ERRORE = "Mi dispiace... ma non ho proprio capito cosa dovrei fare... potresti riscriverlo meglio? :-(";
	
	public ComandoNonRiconosciutoException() {
		super(ERRORE);
	}
}
