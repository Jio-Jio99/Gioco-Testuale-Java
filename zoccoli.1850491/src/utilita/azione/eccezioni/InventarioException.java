package utilita.azione.eccezioni;

public abstract class InventarioException extends AzioneException {
	private static final long serialVersionUID = 1L;

	public InventarioException(String errore) {
		super(errore);
	}
}
