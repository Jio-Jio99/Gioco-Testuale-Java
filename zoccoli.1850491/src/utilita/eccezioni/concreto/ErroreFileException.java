package utilita.eccezioni.concreto;

import utilita.eccezioni.ErroreCaricamentoException;

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
