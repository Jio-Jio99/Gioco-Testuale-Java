package utilita.eccezioni.concreto;

import utilita.eccezioni.MondoFileException;

/**
 * Eccezione lanciata quando è presente un errore di collegamento tra le stanze nel file di caricamento del Mondo <p>
 * Classe derivata da {@link MondoFileException}
 * @author gioele
 *
 */
public class LinkFileException extends MondoFileException {
	public static final String ERRORE =  "Attenzione! È presente un errore di collegamento tra le stanze nel file per il caricamento del mondo";
	
	public LinkFileException() {
		super(ERRORE);	
	}
}
