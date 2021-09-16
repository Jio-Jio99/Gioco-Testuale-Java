package it.uniroma1.textadv.utilita.creazione.eccezioni;

/**
 * Eccezione lanciata in caso vi sia un path scorretto e/o il file sia vuoto
 * @author gioele
 *
 */
public class FileNonEsistenteOVuotoException extends MondoFileException{
	public static final String ERRORE = "Il file selezionato o non è presente nella directory, o è vuoto!";
	
	public FileNonEsistenteOVuotoException() {
		super(ERRORE);
	}
}
