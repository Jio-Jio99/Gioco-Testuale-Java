package it.uniroma1.textadv.utilita.creazione.eccezioni.concreto;

import it.uniroma1.textadv.utilita.creazione.eccezioni.MondoFileException;

/**
 * Eccezione lanciata quando è presente un errore di posizione di un it.uniroma1.textadv.entita nel file di caricamento del Mondo <p>
 * Classe derivata da {@link MondoFileException}
 * @author gioele
 *
 */
public class PosizioneFileException extends MondoFileException {
	public static final String ERRORE = "Attenzione! Nel file per il caricamento del mondo, uno stesso oggetto si trova in due posizioni diverse: ";
	
	public PosizioneFileException(String entita) {
		super(ERRORE + entita);
	}
}
