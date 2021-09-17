package it.uniroma1.textadv.utilita.funzionamento;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Dare;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Movimento;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Osservazione;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Prendere;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Usare;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.AccessoNonDisponibileException;
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
	 * Comando ricevuto splittato in nonHoOggetto
	 */
	private static List<String> comando;
	
	/**
	 * i nomi di tutte le entita del mondo in cui si gioca
	 */
	private static Set<Entita> entitaNelMondo;
	
	/**
	 * Mondo in cui si gioca
	 */
	private static Mondo mondo;
	
	/**
	 * Stanza dove si trova il giocatore
	 */
	private Stanza stanza;
	
	/**
	 * Azione che si sta analizzando
	 */
	private Azione azione;
	
	
	public AnalizzaComando(Mondo mondoNuovo) {
		mondo = mondoNuovo;
		entitaNelMondo = mondo.getEntita();
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

		if(comando.isEmpty())
			throw new ComandoNonRiconosciutoException();
		
		//prendo l'azione da fare tramite la prima parola
		azione = Azione.getAzione(comando.get(0));
		
		if(azione == null)
			throw new ComandoNonRiconosciutoException();

		List<Entita> entita = entitaDisponibili(azione.entita(comando, entitaNelMondo));

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
			azione.active(entita.isEmpty() ? stanza : entita.get(0),entita.isEmpty() ? null : entita.subList(1, entita.size()).toArray(Entita[]::new));
		}
		catch(ClassCastException e) {
			throw new IncoerenzaEntitaAzioneException(azione, entita);
		}
	}
	
	
	/**
	 * Metodo che presa una lista di entita, controlla se sono presenti nella stanza, altrimenti lancia un eccezione
	 * @param nomiEntita
	 * @return
	 * @throws GiocatoreException
	 * @throws OggettoNonInStanzaException
	 * @throws AccessoNonDisponibileException 
	 */
	private List<Entita> entitaDisponibili(List<Entita> entitaTrovate) throws GiocatoreException, AzioneException {
		String nomeEntita = "";
		List<Entita> lista = new LinkedList<>();

		for(Entita entita : entitaTrovate) {
			nomeEntita = entita.getNome();
			if(entita instanceof Stanza && stanza.verificaAccessoLibero(nomeEntita)) 
				lista.add(stanza.getAccessoLibero(nomeEntita));
			else if(stanza.getEntita(nomeEntita) || Giocatore.getInstance().getEntita(nomeEntita)) {
				if(azione instanceof Prendere && !comando.contains(Prendere.DA))
					lista.addAll(List.of(entita, Prendere.daChi(stanza, nomeEntita)));
				else
					lista.add(entita);
			}
			else 
				throw new OggettoNonInStanzaException(entita);
		}

		return lista;
	}
	
	/**
	 * Metodo che trasforma una stringa nella lista di nonHoOggetto contenute
	 * @param stringa 
	 * @return
	 */
	public static List<String> stringInList(String stringa){
		return Arrays.asList(stringa.split(" ")).stream().map(String::strip).filter(Predicate.not(String::isBlank)).collect(Collectors.toList());
	}
}