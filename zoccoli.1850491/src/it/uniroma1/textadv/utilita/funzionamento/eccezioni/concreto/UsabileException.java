package it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto;

import it.uniroma1.textadv.entita.interfaccia.Utilizzato;
import it.uniroma1.textadv.entita.interfaccia.Utilizzatore;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

/**
 * Eccezione in caso in cui le due entita non siano combatibili per essere usate assieme
 * @author gioele
 *
 */
public class UsabileException extends AzioneException {
	public static final String ERRORE = "Errore! Non puoi utilizzare questi strumenti tra loro!!";
	
	public UsabileException(Utilizzatore strum1, Utilizzato strum2) {
		super(ERRORE + " Tra " + strum1 + " e " +  strum2);
	}
}
