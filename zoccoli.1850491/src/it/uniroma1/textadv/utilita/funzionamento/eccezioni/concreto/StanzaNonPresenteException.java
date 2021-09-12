package it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto;

import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public class StanzaNonPresenteException  extends AzioneException{
	public static final String ERRORE = "La stanza richiesta non è presente in questo mondo!";
	
	public StanzaNonPresenteException() {
		super(ERRORE);
	}

}
