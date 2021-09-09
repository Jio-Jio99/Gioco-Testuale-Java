package it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto;

import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

/**
 * Eccezione lanciata in caso non esista la direzione inserita, o sia scritta errata
 * @author gioele
 *
 */
public class PuntoCardinaleException extends AzioneException{
	public static final String ERRORE = "Punto cardinale inserito scoretto!";
	
	public PuntoCardinaleException() {
		super(ERRORE);
	}
}
