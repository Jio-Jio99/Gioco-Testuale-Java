package it.uniroma1.textadv.utilita.azione.eccezioni.concreto;

import it.uniroma1.textadv.utilita.azione.eccezioni.AzioneException;

public class ComandoScrittoNonCorrettamenteException extends AzioneException{
	public static final String ERRORE = "Forse hai scritto male il comando, ma provo comunque a vedere se riesco a capire cosa fare!";
	
	public ComandoScrittoNonCorrettamenteException() {
		super(ERRORE);
	}
}