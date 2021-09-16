package it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto;

import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;


/**
 * Eccezione lanciata in caso in cui il comando sia scritto in modo incoerente o difficile da capire per il compilatore
 * @author gioele
 *
 */
public class ComandoScrittoNonCorrettamenteException extends AzioneException{
	public static final String ERRORE = "Forse hai scritto male il comando, ho capito cosa dovrebbe fare ma non so con cosa";
	
	public ComandoScrittoNonCorrettamenteException() {
		super(ERRORE);
	}
}
