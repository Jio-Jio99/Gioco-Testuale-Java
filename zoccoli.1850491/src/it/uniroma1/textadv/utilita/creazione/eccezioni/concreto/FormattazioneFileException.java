package it.uniroma1.textadv.utilita.creazione.eccezioni.concreto;

import it.uniroma1.textadv.utilita.creazione.eccezioni.MondoFileException;

/**
 * Eccezione lanciata quando è presente un errore di formattazione nel file e che non segue il pattern <p>
 * Classe derivata da {@link MondoFileException}
 * @author gioele
 *
 */
public class FormattazioneFileException extends MondoFileException{
	public static final String ERRORE = "Attenzione! È presente un errore di formattazione nel file per il caricamento del mondo nella parte: ";
	
	
	public FormattazioneFileException(String errore) {
		super(ERRORE + errore);
	}
}