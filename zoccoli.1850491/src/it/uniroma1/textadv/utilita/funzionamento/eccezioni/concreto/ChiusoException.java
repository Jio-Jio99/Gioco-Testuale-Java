package it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto;

import it.uniroma1.textadv.entita.interfaccia.Apribile;
import it.uniroma1.textadv.entita.link.Link;
import it.uniroma1.textadv.entita.oggetto.Contenitore;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;


/**
 * Eccezione lanciata in caso in cui sia un {@link Contenitore} sia un {@link Link} siano chiusi e non sia possibile prendere l'oggetto o passare da una stanza ad un'altra nel secondo caso
 * @author gioele
 *
 */
public class ChiusoException extends AzioneException{
	public static final String ERRORE = " è chiuso!";
	public static final String ERRORE_CHIAVE = " è chiuso a chiave!";

	public ChiusoException(Apribile nome, boolean chiave) {
		super(nome + (chiave ? ERRORE_CHIAVE : ERRORE));
	}
}
