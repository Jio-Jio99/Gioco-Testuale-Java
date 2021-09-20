package it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.stanza.Stanza;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

/**
 * Eccezione lanciata in caso in cui l'entita chiamata non sia presente nella stanza
 * @author gioele
 *
 */
public class OggettoNonInStanzaException extends AzioneException{
	public static final String ERRORE = "L'entita richiesta non si trova nella stanza!";
	public static final String ERRORE_2 = "Errore! Non puoi fare questa azione con questa stanza, esse sono solo visibili con 'guarda' o 'osserva'";
	private static Entita entita;
	
	public OggettoNonInStanzaException(Entita entita) {
		this.entita = entita;
	}
	
	public OggettoNonInStanzaException() {
		entita = null;
	}
	
	@Override
	public String getMessage() {
		return entita instanceof Stanza ? ERRORE_2 : ERRORE; 
	}
}
