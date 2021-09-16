package it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto;

import it.uniroma1.textadv.entita.interfaccia.Apribile;
import it.uniroma1.textadv.entita.oggetto.Oggetto;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;


/**
 * Eccezione lanciata in caso in cui si stia cercando di aprire un {@link Apribile} con delle chiavi non corrispondenti
 * @author gioele
 *
 */
public class ChiaveNonCorrispondenteException extends AzioneException{

	public ChiaveNonCorrispondenteException(Oggetto e, Apribile c) {
		super("L'oggetto " + e.getNome() + " non può aprire " + c);
	}
}
