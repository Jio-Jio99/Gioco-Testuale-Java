package it.uniroma1.textadv.utilita.creazione.eccezioni;

public class FileNonEsistenteOVuotoException extends MondoFileException{
	public static final String ERRORE = "Il file selezionato o non è presente nella directory, o è vuoto!";
	
	public FileNonEsistenteOVuotoException() {
		super(ERRORE);
	}
}
