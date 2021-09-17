package it.uniroma1.textadv.entita;

/**
 * Enumerazione dello stato di gioco
 * @see IN_GIOCO 
 * @see PERSO 
 * @see VINTO 
 * @see VINTO_CON_BONUS 
 * @author gioele
 *
 */
public enum StatoGioco {
	/**
	 * Il giocatore è ancora in gioco
	 */
	IN_GIOCO,
	
	/**
	 * Il giocatore ha concluso le mosse, ma non ha trovato il tesoro
	 */
	PERSO,
	
	/**
	 * Il giocatore ha troavto il tesoro, ma dentro non c'è il bonus
	 */
	VINTO,
	
	/**
	 * Il giocatore ha trovato il tesoro con dentro il bonus
	 */
	VINTO_CON_BONUS;
}
