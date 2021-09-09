package it.uniroma1.textadv.utilita.azione.eccezioni.concreto;

import it.uniroma1.textadv.utilita.azione.eccezioni.AzioneException;

public class OggettoNonInStanzaException extends AzioneException{
	public static final String ERRORE = "L'it.uniroma1.textadv.entita richiesta non è nel luogo dove si trova!";
	
	public OggettoNonInStanzaException(){
		super(ERRORE);
	}
}
