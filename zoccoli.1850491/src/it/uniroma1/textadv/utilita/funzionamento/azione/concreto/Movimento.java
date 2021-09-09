package it.uniroma1.textadv.utilita.funzionamento.azione.concreto;

import java.util.List;
import java.util.Set;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.PuntoCardinale;
import it.uniroma1.textadv.entita.link.Link;
import it.uniroma1.textadv.entita.personaggio.concreto.Giocatore;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.azione.Azione;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.AccessoNonDisponibileException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.PuntoCardinaleException;

public class Movimento extends Azione{
	public static final Set<String> COMANDI = Set.of("vai", "entra");
	
	public Movimento() {
		super(COMANDI);
	}
	
	public PuntoCardinale getDirezione(List<String> comando) throws PuntoCardinaleException {
		for(String s : comando) {
			try {
				return PuntoCardinale.getDirezione(s);
			} catch (PuntoCardinaleException e1) {continue;}
		}
		
		throw new PuntoCardinaleException();
	}
	
	@Override
	public boolean contains(String parola) {
		if(super.contains(parola))
			return true;
		
		try {
			PuntoCardinale.getDirezione(parola);
			return true;
		} 
		catch (PuntoCardinaleException e) {
			return false;
		}
	}
	
	@Override
	public void active(List<Entita> entita) throws AzioneException, GiocatoreException {
		if(entita.isEmpty())
			throw new AccessoNonDisponibileException();
		
		Giocatore.getInstance().vai((Link) entita.get(0));
	}
}
