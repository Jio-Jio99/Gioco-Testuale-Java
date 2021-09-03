package utilita.creazione.eccezioni.concreto;

import utilita.creazione.eccezioni.ErroreCaricamentoException;

/**
 * Eccezione generale che incapsula l'errore nel file 
 * @author gioele
 *
 */
public class ErroreFileException extends ErroreCaricamentoException {
	
	public ErroreFileException(String errore) {
		super(errore);
	}
	
}
