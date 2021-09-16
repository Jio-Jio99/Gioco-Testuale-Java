package it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto;

import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public class CaminoException extends AzioneException{
	public static final String ERRORE = "Il camino è acceso! Così ti bruci!";
	
	public CaminoException() {
		this(ERRORE);
	}
	
	public CaminoException(String errore) {
		super(errore);
	}
}
