package it.uniroma1.textadv.utilita.creazione.eccezioni.concreto;

import it.uniroma1.textadv.utilita.creazione.eccezioni.MondoFileException;

/**
 * Errore lanciato in caso non sia presente come classe .java l'it.uniroma1.textadv.entita richiesta da instanziare e/o da chiamare
 * @author gioele
 *
 */
public class EntitaException extends MondoFileException{
	public static final String ERRORE = " non esiste in questo programma";
	
	public EntitaException(String nomeEntita) {
		super("Errore! " + nomeEntita + ERRORE);
	}
}
