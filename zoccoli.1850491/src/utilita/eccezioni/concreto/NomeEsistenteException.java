package utilita.eccezioni.concreto;

import utilita.eccezioni.MondoFileException;

public class NomeEsistenteException extends MondoFileException{
	public static final String ERRORE = "Questo nome è gia esistente! Errore di formattazione file";
	
	public NomeEsistenteException() {
		super(ERRORE);
	}
}
