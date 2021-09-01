package utilita.eccezioni.concreto;

import utilita.eccezioni.MondoFileException;

/**
 * Errore lanciato in caso vi siano due entita con lo stesso nome
 * @author gioele
 *
 */
public class NomeEsistenteException extends MondoFileException{
	public static final String ERRORE = "Nome gia esistente! Errore di formattazione file: ";
	
	public NomeEsistenteException(String nome) {
		super(ERRORE + nome);
	}
}
