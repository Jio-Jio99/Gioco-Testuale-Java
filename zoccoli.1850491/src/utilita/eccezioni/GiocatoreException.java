package utilita.eccezioni;

public abstract class GiocatoreException extends Exception{
	
	public GiocatoreException(String nome) {
		super(nome);
	}
}
