package utilita.eccezioni.concreto;

import utilita.eccezioni.MondoFileException;

public class EntitaException extends MondoFileException{
	public static final String ERRORE = " non esiste in questo programma";
	
	public EntitaException(String nomeEntita) {
		super("Errore! " + nomeEntita + ERRORE);
	}
}
