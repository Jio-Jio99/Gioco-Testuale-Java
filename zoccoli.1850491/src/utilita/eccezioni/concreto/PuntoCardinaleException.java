package utilita.eccezioni.concreto;

public class PuntoCardinaleException extends Exception{
	public static final String ERRORE = "Punto cardinale inserito scorettamente!";
	
	public PuntoCardinaleException() {
		super(ERRORE);
	}
}
