package it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto;

import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

/**
 * Eccezione lanciata in caso in cui l'entita chiamata non sia presente nella stanza
 * @author gioele
 *
 */
public class OggettoNonInStanzaException extends AzioneException{
	public static final String ERRORE = "L'entita richiesta non è in tuo possesso o non si trova nella stanza!";
	
	public OggettoNonInStanzaException(){
		super(ERRORE + " Cerca meglio! Magari la troverai in altre stanze");
	}
	
	public OggettoNonInStanzaException(String nomeOggetto) {
		super(ERRORE + " Forse troverai in altre stanze " + nomeOggetto + "!");
	}
}
