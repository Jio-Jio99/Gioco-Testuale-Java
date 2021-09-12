package it.uniroma1.textadv.utilita.funzionamento;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.Mondo;
import it.uniroma1.textadv.entita.PuntoCardinale;
import it.uniroma1.textadv.entita.personaggio.concreto.Giocatore;
import it.uniroma1.textadv.entita.stanza.Stanza;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.azione.Azione;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Aprire;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Movimento;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Osservazione;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Prendere;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Usare;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.ComandoNonRiconosciutoException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.ComandoScrittoNonCorrettamenteException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.EntitaNonDiQuestoMondoException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.IncoerenzaEntitaAzioneException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.OggettoNonInStanzaException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.PuntoCardinaleException;

/**
 * Classe creata da un mondo specifico, che analizza il comando ricevuto per mettere in azione il giocatore
 * @author gioele
 *
 */
public class AnalizzaComando {
	/**
	 * Comando ricevuto splittato in parole
	 */
	private static List<String> comando;
	
	/**
	 * i nomi di tutte le entita del mondo in cui si gioca
	 */
	private static Set<List<String>> entitaNelMondo;
	
	/**
	 * Mondo in cui si gioca
	 */
	private static Mondo mondo;
	
	/**
	 * Stanza dove si trova il giocatore
	 */
	private Stanza stanza;
	
	public AnalizzaComando(Mondo mondoNuovo) {
		mondo = mondoNuovo;
		entitaNelMondo = mondo.getEntitaString();
	}
	
	/**
	 * Metodo che preso in input una stringa di comando, analizza l'azione e la mette in moto
	 * @param comandoString
	 * @throws AzioneException
	 * @throws GiocatoreException
	 * @throws PuntoCardinaleException 
	 */
	public void analizzaComando(String comandoString) throws AzioneException, GiocatoreException, PuntoCardinaleException {
		stanza = Giocatore.getInstance().getPosizione();
		comando = stringInList(comandoString);
		
		//prendo l'azione da fare tramite la prima parola
		Azione azione = Azione.getAzione(comando.get(0));
		
		if(azione == null)
			throw new ComandoNonRiconosciutoException();

		List<Entita> entita = trasformaString(cercaEntita());

		if(entita.isEmpty() && !(azione instanceof Osservazione)) {
			if(azione instanceof Movimento) {
				PuntoCardinale punto = ((Movimento)azione).getDirezione(comando);
				entita.add(stanza.getAccesso(punto));
			}
			else if(comando.size() == 1) {
				throw new ComandoScrittoNonCorrettamenteException();
			}
			else
				throw new EntitaNonDiQuestoMondoException();
		}
		
		try{
			azione.active(entita);
		}
		catch(ClassCastException e) {
			throw new IncoerenzaEntitaAzioneException();
		}
	}
	
	/**
	 * Metodo che cerca se vi sono nomi di entita nei comandi ricevuti, e ne restituisce una lista ordinata
	 * @return
	 * @throws GiocatoreException
	 */
	private List<String> cercaEntita() throws GiocatoreException {
		List<String> lista = new LinkedList<>();
		
		int entita2 = Collections.indexOfSubList(comando, List.of(Aprire.CON));
		
		if(entita2 == -1) {
			entita2 = Collections.indexOfSubList(comando, List.of(Usare.SU));
			
			if(entita2 == -1)
				entita2 = Collections.indexOfSubList(comando, List.of(Prendere.DA));
		}
		
		if(entita2 != -1) {
			lista.add(entita(comando.subList(0, entita2)));
			lista.add(entita(comando.subList(entita2, comando.size())));
		}
		else
			lista.add(entita(comando));
		
		lista.removeAll(List.of(""));
		
		return lista;
	}
	
	/**
	 * Metodo che analizza il comando ricevuto, e ne restituisce la prima entita riconosciuta
	 * @param parteDiComando
	 * @return
	 */
	private String entita(List<String> parteDiComando) {
		for(List<String> nomeEntita : entitaNelMondo) {
			if(Collections.indexOfSubList(parteDiComando, nomeEntita) != -1) 
				return nomeEntita.stream().collect(Collectors.joining(" "));
		}
		
		return "";
	}
	
	/**
	 * Metodo che presa una lista di nomi di entita, ne ritorna una lista con i riferimenti delle entita
	 * @param nomiEntita
	 * @return
	 * @throws GiocatoreException
	 * @throws OggettoNonInStanzaException
	 */
	private List<Entita> trasformaString(List<String> nomiEntita) throws GiocatoreException, OggettoNonInStanzaException {
		List<Entita> lista = new LinkedList<>();
		Entita e = null;
		
		for(String nome : nomiEntita) {
			e = stanza.getEntita(nome);
			
			if(e == null)
				e = Giocatore.getInstance().getEntita(nome);
			
			if(e == null)
				throw new OggettoNonInStanzaException(nome);
			
			lista.add(e);
		}
		
		return lista;
	}
	
	/**
	 * Metodo che trasforma una stringa nella lista di parole contenute
	 * @param stringa 
	 * @return
	 */
	public static List<String> stringInList(String stringa){
		return Arrays.asList(stringa.split(" ")).stream().map(String::strip).filter(Predicate.not(String::isBlank)).collect(Collectors.toList());
	}
}