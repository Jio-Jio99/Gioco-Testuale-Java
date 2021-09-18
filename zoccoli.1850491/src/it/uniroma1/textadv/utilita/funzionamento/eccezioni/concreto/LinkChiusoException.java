package it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto;

import it.uniroma1.textadv.entita.link.Link;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

/**
 * Eccezione lanciata in caso in cui il passaggio del {@link Link} sia chiuso
 * @author gioele
 *
 */
public class LinkChiusoException extends AzioneException{
	public static final String ERRORE = "Il passaggio è chiuso! Aprilo prima!";
	
	public LinkChiusoException() {
		super(ERRORE);
	}
	
}
