package it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto;

import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public class ComandoScrittoNonCorrettamenteException extends AzioneException{
	public static final String ERRORE = "Forse hai scritto male il comando, ho capito cosa dovrebbe fare ma non so con cosa";
	
	public ComandoScrittoNonCorrettamenteException() {
		super(ERRORE);
	}
}
