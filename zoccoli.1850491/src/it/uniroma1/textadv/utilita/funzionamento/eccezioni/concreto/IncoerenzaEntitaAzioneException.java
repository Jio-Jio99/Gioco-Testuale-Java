package it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto;

import java.util.List;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.utilita.funzionamento.azione.Azione;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public class IncoerenzaEntitaAzioneException extends AzioneException{
	
	public IncoerenzaEntitaAzioneException(Azione azione, List<Entita> entita) {
		super("Non puoi eseguire l'azione di " + azione + " con " + entita.toString().replaceAll("[\\[\\]]", "") + "... mi sembra strano!");
	}
}
