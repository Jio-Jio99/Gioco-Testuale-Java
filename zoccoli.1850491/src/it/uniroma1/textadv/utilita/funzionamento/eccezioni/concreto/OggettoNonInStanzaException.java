package it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto;

import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public class OggettoNonInStanzaException extends AzioneException{
	public static final String ERRORE = "L'entita richiesta non è in tuo possesso o non si trova nella stanza! Cerca meglio, magari la trovi";
	
	public OggettoNonInStanzaException(){
		super(ERRORE);
	}
}
