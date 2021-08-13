package utilita.eccezioni.concreto;

import utilita.eccezioni.MondoFileException;

/**
 * Eccezione lanciata quando è presente un errore di posizione di un entita nel file di caricamento del Mondo <p>
 * Classe derivata da {@link MondoFileException}
 * @author gioele
 *
 */
public class PosizioneFileException extends MondoFileException {
	public static final String ERRORE = "Attenzione! Nel file per il caricamento del mondo, uno stesso oggetto si trova in due posizioni diverse!";
	
	public PosizioneFileException() {
		super(ERRORE);
	}
}
