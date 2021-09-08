package utilita.azione.eccezioni.concreto;

import utilita.azione.eccezioni.InventarioException;

public class OggettoNonInInventarioException extends InventarioException {
	public static final String ERRORE = "L'oggetto richiesto non è nell'inventario!";
	
	public OggettoNonInInventarioException() {
		super(ERRORE);
	}
}
