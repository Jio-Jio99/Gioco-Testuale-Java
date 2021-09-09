package it.uniroma1.textadv.utilita.creazione.eccezioni.concreto;

import it.uniroma1.textadv.utilita.creazione.eccezioni.ErroreCaricamentoException;

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
