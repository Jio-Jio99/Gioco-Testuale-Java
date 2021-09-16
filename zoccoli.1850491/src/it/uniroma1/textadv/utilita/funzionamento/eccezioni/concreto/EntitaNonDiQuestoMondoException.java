package it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto;

import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;


/**
 * Eccezione lanciata in caso in cui l'entita richiesta non sia presente nel mondo caricato
 * @author gioele
 *
 */
public class EntitaNonDiQuestoMondoException extends AzioneException {
	public static final String ERRORE = "L'Entita richiesta non è di questo MONDO!! UAOH, quindi non posso farci nulla...";
	
	public EntitaNonDiQuestoMondoException() {
		super(ERRORE);
	}

}
