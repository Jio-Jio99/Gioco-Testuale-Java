package utilita.azione.eccezioni.concreto;

import utilita.azione.eccezioni.AzioneException;

public class IncoerenzaEntitaAzioneException extends AzioneException{
	public static final String ERRORE = "Sembra ci sia un incoerenza tra l'azione e l'entita";
	
	public IncoerenzaEntitaAzioneException() {
		super(ERRORE);
	}
}
