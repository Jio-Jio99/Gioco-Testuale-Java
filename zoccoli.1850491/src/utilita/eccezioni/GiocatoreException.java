package utilita.eccezioni;

public abstract class GiocatoreException extends Exception{
	private static final long serialVersionUID = 1L;

	public GiocatoreException(String nome) {
		super(nome);
	}
}
