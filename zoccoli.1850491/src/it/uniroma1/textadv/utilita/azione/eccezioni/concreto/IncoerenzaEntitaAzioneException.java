package it.uniroma1.textadv.utilita.azione.eccezioni.concreto;

import it.uniroma1.textadv.utilita.azione.eccezioni.AzioneException;

public class IncoerenzaEntitaAzioneException extends AzioneException{
	public static final String ERRORE = "Sembra ci sia un incoerenza tra l'azione e l'it.uniroma1.textadv.entita";
	
	public IncoerenzaEntitaAzioneException() {
		super(ERRORE);
	}
}
