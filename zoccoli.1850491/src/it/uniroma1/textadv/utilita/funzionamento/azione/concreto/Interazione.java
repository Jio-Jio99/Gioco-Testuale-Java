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

	@Override
	public void active(Entita... entita) throws AzioneException, GiocatoreException {
		((Personaggio) entita[0]).interazione();
	}
}
