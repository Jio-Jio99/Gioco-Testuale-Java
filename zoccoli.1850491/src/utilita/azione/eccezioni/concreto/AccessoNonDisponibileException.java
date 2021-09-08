package utilita.azione.eccezioni.concreto;

import utilita.azione.eccezioni.AzioneException;

public class AccessoNonDisponibileException extends AzioneException{
	public static final String ERRORE = "Ehm.... non puoi andare da quel...BOOM, ops...hai sbattuto al muro, ti stavo avvertendo!";
	
	public AccessoNonDisponibileException() {
		super(ERRORE);
	}
}
