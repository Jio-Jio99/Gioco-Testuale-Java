package it.uniroma1.textadv.utilita.creazione.eccezioni.concreto;

import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;

/**
 * Eccezione lanciata in caso si stia chiamando un giocatore con nome diverso da quello instanziato inizialmente
 * @author gioele
 *
 */
public class GiocatoreNomeDiversoException extends GiocatoreException {
	public static final String ERRORE = "È stato inserito un nome diverso rispetto alla prima instanza del giocatore!"
			+ " Non verrà valutato il nuovo nome";
	
	public GiocatoreNomeDiversoException() {
		super(ERRORE);
	}
}
