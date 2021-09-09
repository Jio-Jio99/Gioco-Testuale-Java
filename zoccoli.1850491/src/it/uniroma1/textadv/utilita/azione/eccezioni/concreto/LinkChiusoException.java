package it.uniroma1.textadv.utilita.azione.eccezioni.concreto;

import it.uniroma1.textadv.utilita.azione.eccezioni.AzioneException;

public class LinkChiusoException extends AzioneException{
	public static final String ERRORE = "Il passaggio è chiuso! Trova qualcosa per aprirlo";
	
	public LinkChiusoException() {
		super(ERRORE);
	}
	
}
