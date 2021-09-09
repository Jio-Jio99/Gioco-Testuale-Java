package it.uniroma1.textadv.utilita.funzionamento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.Mondo;
import it.uniroma1.textadv.entita.link.concreto.Libero;
import it.uniroma1.textadv.entita.personaggio.concreto.Giocatore;
import it.uniroma1.textadv.entita.stanza.Stanza;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.enumerazioni.PuntoCardinale;
import it.uniroma1.textadv.utilita.funzionamento.azione.Azione;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Movimento;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Osservazione;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.ComandoNonRiconosciutoException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.ComandoScrittoNonCorrettamenteException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.IncoerenzaEntitaAzioneException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.OggettoNonInStanzaException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.PuntoCardinaleException;

public abstract class AnalizzaComando {
	private static List<String> comando;

	/**
	 * 
	 * @param mondo
	 * @param comandoString
	 * @throws AzioneException
	 * @throws GiocatoreException
	 * @throws PuntoCardinaleException 
	 */
	public static void analizzaComando(Mondo mondo, String comandoString) throws AzioneException, GiocatoreException, PuntoCardinaleException {
		List<Entita> disponibili = new ArrayList<>();
		Stanza stanza = Giocatore.getInstance().getPosizione();
		comando = stringInList(comandoString);
		
		//prendo l'azione da fare tramite la prima parola
		Azione azione = Azione.getAzione(comando.get(0));
		
		if(azione == null)
			throw new ComandoNonRiconosciutoException();

		disponibili = cercaEntita(stanza.getEntita());
		
		if(disponibili.isEmpty() && !(azione instanceof Osservazione)) {
			if(azione instanceof Movimento) {
				PuntoCardinale punto = ((Movimento)azione).getDirezione(comando);
				disponibili.add(stanza.getAccesso(punto));
			}
			else if(comando.size() == 1) {
				throw new ComandoScrittoNonCorrettamenteException();
			}
			else 
				throw new OggettoNonInStanzaException();
		}
		
		
		try{
			azione.active(disponibili.toArray(Entita[]::new));
		}
		catch(ClassCastException e) {
			throw new IncoerenzaEntitaAzioneException();
		}
	}
	
	/**
	 * Metodo che trasforma una stringa nella lista di parole contenute
	 * @param stringa 
	 * @return
	 */
	private static List<String> stringInList(String stringa){
		return Arrays.asList(stringa.split(" ")).stream().map(String::strip).filter(Predicate.not(String::isBlank)).collect(Collectors.toList());
	}
	
	
	private static List<Entita> cercaEntita(Set<Entita> setEntita) throws GiocatoreException {
		List<Entita> lista = new ArrayList<>();
		List<String> supporto;
		
		Set<Entita> inInventario = Giocatore.getInstance().getInventario().values().stream().map(x ->(Entita) x).collect(Collectors.toSet());
		
		setEntita.addAll(inInventario);
		
		for(Entita e : setEntita) {
			supporto = stringInList(e.getNome());
			if(Collections.indexOfSubList(comando, supporto)!=-1 && !(e instanceof Stanza))
				lista.add(e);
		}
		
		return lista;
	}
	
}