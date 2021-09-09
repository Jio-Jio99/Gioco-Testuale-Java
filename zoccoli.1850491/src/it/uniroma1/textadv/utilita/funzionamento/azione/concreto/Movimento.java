package it.uniroma1.textadv.utilita.funzionamento.azione.concreto;

import java.util.Set;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.link.Link;
import it.uniroma1.textadv.entita.personaggio.concreto.Giocatore;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.azione.Azione;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.AccessoNonDisponibileException;

public class Movimento extends Azione{
	public static final Set<String> COMANDI = Set.of("vai", "entra");
	
	public Movimento() {
		super(COMANDI);
	}
	
	@Override
	public void active(Entita... entita) throws AzioneException, GiocatoreException {
		if(entita.length == 0 || entita[0] == null)
			throw new AccessoNonDisponibileException();
		
		Giocatore.getInstance().vai((Link) entita[0]);
	}
}
