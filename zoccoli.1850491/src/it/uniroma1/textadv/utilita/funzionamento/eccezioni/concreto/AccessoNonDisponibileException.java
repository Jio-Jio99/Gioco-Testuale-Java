package it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto;

import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

/**
 * Eccezione lanciata nel caso in cui si voglia andare in una direzione dove non ci sono accessi
 * @author gioele
 *
 */
public class AccessoNonDisponibileException extends AzioneException{
	public static final String ERRORE = "Ehm.... non puoi andare da quel...BOOM, ops...hai sbattuto al muro, ti stavo avvertendo!";
	
	public AccessoNonDisponibileException() {
		super(ERRORE);
	}
}
