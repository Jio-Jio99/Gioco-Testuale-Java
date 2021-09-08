package utilita.azione.eccezioni.concreto;

import utilita.azione.eccezioni.AzioneException;

public class ComandoNonRiconosciutoException extends AzioneException{
	public static final String ERRORE = "Mi dispiace... ma non ho proprio capito cosa dovrei fare... potresti riscriverlo meglio? :-(";
	
	public ComandoNonRiconosciutoException() {
		super(ERRORE);
	}
}
