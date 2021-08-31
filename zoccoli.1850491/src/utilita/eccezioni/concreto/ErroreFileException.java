package utilita.eccezioni.concreto;

import utilita.eccezioni.ErroreCaricamentoException;

public class ErroreFileException extends ErroreCaricamentoException {
	
	public ErroreFileException(String errore) {
		super(errore);
	}
	
}
