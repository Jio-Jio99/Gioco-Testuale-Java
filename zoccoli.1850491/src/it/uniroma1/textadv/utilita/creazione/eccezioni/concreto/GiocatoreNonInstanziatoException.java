package it.uniroma1.textadv.utilita.creazione.eccezioni.concreto;

import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;

/**
 * Eccezione lanciata in caso di mancata creazione del giocatore 
 * @author gioele
 *
 */
public class GiocatoreNonInstanziatoException extends GiocatoreException{
	public static final String 	ERRORE = "Il giocatore non è stato ancora instanziato, quindi inserire un nome!";
	
	public GiocatoreNonInstanziatoException() {
		super(ERRORE);
	}
}
