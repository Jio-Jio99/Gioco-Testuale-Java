package utilita.creazione.eccezioni.concreto;

import utilita.creazione.eccezioni.GiocatoreException;

public class GiocatoreNonInstanziatoException extends GiocatoreException{
	public static final String 	ERRORE = "Il giocatore non è stato ancora instanziato, quindi inserire un nome!";
	
	public GiocatoreNonInstanziatoException() {
		super(ERRORE);
	}
}
