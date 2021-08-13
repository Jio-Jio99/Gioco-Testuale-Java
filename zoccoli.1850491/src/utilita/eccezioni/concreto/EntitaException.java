package utilita.eccezioni.concreto;

import utilita.eccezioni.MondoFileException;

public class EntitaException extends MondoFileException{
	public static final String ERRORE = "Errore di entita', non esiste in questo programma";
	
	public EntitaException() {
		super(ERRORE);
	}
}
