package it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto;

import java.util.List;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.utilita.funzionamento.azione.Azione;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

/**
 * Eccezione lanciata in caso in cui l'azione non sia possibile con l'entita selezionate
 * @author gioele
 *
 */
public class IncoerenzaEntitaAzioneException extends AzioneException{
	public static final String ERRORE = "Azione e entita insieme scorretti!!";
	
	public IncoerenzaEntitaAzioneException(Azione azione, List<Entita> entita) {
		super("Non puoi eseguire l'azione di " + azione + " con " + entita.toString().replaceAll("[\\[\\]]", "") + "... mi sembra strano!");
	}
	
	
	public IncoerenzaEntitaAzioneException() {
		super(ERRORE);
	}
}
