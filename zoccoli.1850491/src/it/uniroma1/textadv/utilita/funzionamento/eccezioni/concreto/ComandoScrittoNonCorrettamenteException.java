package it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto;

import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;


/**
 * Eccezione lanciata in caso in cui il comando sia scritto in modo incoerente o difficile da capire per il compilatore
 * @author gioele
 *
 */
public class ComandoScrittoNonCorrettamenteException extends AzioneException{
	public static final String ERRORE = "Forse hai scritto male il comando, ho capito cosa dovrebbe fare ma non so con cosa";
	public static final String ERRORE_CAPITO = "Comando riconosciuto che esguirò ugualmente, ma in default, poichè l'entita inserita non esiste";
	private boolean compreso;
	
	public ComandoScrittoNonCorrettamenteException() {}
	
	public ComandoScrittoNonCorrettamenteException(boolean compreso) {
		this.compreso = compreso;
	}
	
	
	@Override
	public String getMessage() {
		return compreso ?  ERRORE_CAPITO : ERRORE;
	}
}
