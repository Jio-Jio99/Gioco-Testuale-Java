package utilita.azione.eccezioni.concreto;

import utilita.azione.eccezioni.AzioneException;

public class ContenitoreChiusoException extends AzioneException{
	public static final String ERRORE = " è chiuso!";
	
	public ContenitoreChiusoException(String nome) {
		super(ERRORE);
	}
}
