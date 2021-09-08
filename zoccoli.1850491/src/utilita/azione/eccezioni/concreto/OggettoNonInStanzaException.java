package utilita.azione.eccezioni.concreto;

import utilita.azione.eccezioni.AzioneException;

public class OggettoNonInStanzaException extends AzioneException{
	public static final String ERRORE = "L'entita richiesta non è nel luogo dove si trova!";
	
	public OggettoNonInStanzaException(){
		super(ERRORE);
	}
}
