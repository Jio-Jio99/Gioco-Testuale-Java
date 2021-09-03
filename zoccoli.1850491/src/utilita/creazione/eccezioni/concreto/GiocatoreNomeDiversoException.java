package utilita.creazione.eccezioni.concreto;

import utilita.creazione.eccezioni.GiocatoreException;

public class GiocatoreNomeDiversoException extends GiocatoreException {
	public static final String ERRORE = "È stato inserito un nome diverso rispetto alla prima instanza del giocatore!"
			+ " Non verrà valutato il nuovo nome";
	
	public GiocatoreNomeDiversoException() {
		super(ERRORE);
	}
}
