package utilita.eccezioni;

/**
 * Classe generale di errore che potrebbe provenire dalla creazione del mondo analizzando il file
 * @author gioele
 */
public abstract class ErroreCaricamentoException extends Exception{
	
	public ErroreCaricamentoException(String errore) {
		super(errore);
	}
}
