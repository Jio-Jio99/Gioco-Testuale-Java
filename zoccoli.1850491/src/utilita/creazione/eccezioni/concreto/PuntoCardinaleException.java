package utilita.creazione.eccezioni.concreto;

/**
 * Eccezione lanciata in caso non esista la direzione inserita, o sia scritta errata
 * @author gioele
 *
 */
public class PuntoCardinaleException extends Exception{
	public static final String ERRORE = "Punto cardinale inserito scoretto!";
	
	public PuntoCardinaleException() {
		super(ERRORE);
	}
}
