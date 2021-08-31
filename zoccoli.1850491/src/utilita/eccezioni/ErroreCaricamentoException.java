package utilita.eccezioni;

public abstract class ErroreCaricamentoException extends Exception{
	
	public ErroreCaricamentoException(String errore) {
		super(errore);
	}
}
