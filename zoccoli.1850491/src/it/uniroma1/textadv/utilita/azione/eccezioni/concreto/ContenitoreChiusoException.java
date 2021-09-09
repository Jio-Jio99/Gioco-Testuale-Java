package it.uniroma1.textadv.utilita.azione.eccezioni.concreto;

import it.uniroma1.textadv.utilita.azione.eccezioni.AzioneException;

public class ContenitoreChiusoException extends AzioneException{
	public static final String ERRORE = " è chiuso!";
	
	public ContenitoreChiusoException(String nome) {
		super(ERRORE);
	}
}
