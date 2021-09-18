package it.uniroma1.textadv.utilita.funzionamento.azione.concreto;

import java.util.List;
import java.util.Set;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.PuntoCardinale;
import it.uniroma1.textadv.entita.link.Link;
import it.uniroma1.textadv.entita.personaggio.concreto.Giocatore;
import it.uniroma1.textadv.entita.stanza.Stanza;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.azione.Azione;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.AccessoNonDisponibileException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.PuntoCardinaleException;

/**
 * Classe per l'azione del Movimento, per spostare i personaggi tra i vari luoghi
 * @author gioele
 *
 */
public class Movimento extends Azione{
	public static final Set<String> COMANDI = Set.of("vai", "entra");
	
	public Movimento() {
		super(COMANDI);
	}
	
	/**
	 * Metodo che dà la direzione ricevuta la lista di comandi
	 * @param comando
	 * @return
	 * @throws PuntoCardinaleException
	 */
	public PuntoCardinale getDirezione(List<String> comando) throws PuntoCardinaleException {
		for(String s : comando) {
			try {
				return PuntoCardinale.getDirezione(s);
			} catch (PuntoCardinaleException e1) {continue;}
		}
		
		throw new PuntoCardinaleException();
	}
	
	@Override
	public List<Entita> entitaInComando(List<String> comando, Set<Entita> set) throws AzioneException, GiocatoreException {
		List<Entita> lista = super.entitaInComando(comando, set);
		Stanza stanza = Giocatore.getInstance().getPosizione();
		
		if(lista.isEmpty()) {
			PuntoCardinale punto = getDirezione(comando);
			lista.add(stanza.getAccesso(punto));
		}
		else {
			//controllo se tra le entita è presente un nome di una stanza, quindi se è raggiungibile dalla stanza presente e se è un accesso libero
			Entita e = null;
			for(int i=0; i<lista.size(); i++) {
				e = lista.get(i);
				if(e instanceof Stanza) {
					if(stanza.verificaAccessoLibero(e.getNome())) 
						lista.set(i,stanza.getAccessoLibero(e.getNome()));
					else if(e.equals(stanza))
						throw new AzioneException("Già ci sei in questa stanza!");
					else
						throw new AccessoNonDisponibileException();
				}
			}
		}
		
		return lista;
	}
	
	/**
	 * Override del metodo contains padre, per controllare se oltre alle parole di default, non vi siano solamente delle coordinate
	 */
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
	
	/**
	 * Metodo che dato un link ne esegue l'effetto sul giocatore
	 */
	@Override
	public void active(Entita link, Entita... entita2) throws AzioneException, GiocatoreException {
		if(link == null)
			throw new AccessoNonDisponibileException();
		
		Giocatore.getInstance().vai((Link) link);
	}
}
