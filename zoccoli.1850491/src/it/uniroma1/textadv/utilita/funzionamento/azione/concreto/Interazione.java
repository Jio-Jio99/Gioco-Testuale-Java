package it.uniroma1.textadv.utilita.funzionamento.azione.concreto;

import java.util.Set;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.personaggio.Personaggio;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.azione.Azione;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public class Interazione extends Azione{
	public static final Set<String> COMANDI = Set.of("parla", "accarezza", "interagisci");
	
	public Interazione() {
		super(COMANDI);
	}

	/**
	 * Metodo che riceve un personaggio e ne attiva l'interazione con il giocatore
	 */
	@Override
	public void active(Entita personaggio, Entita...entita2) throws AzioneException, GiocatoreException {
		((Personaggio) personaggio).interazione();
	}
}
