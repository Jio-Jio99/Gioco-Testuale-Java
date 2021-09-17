package it.uniroma1.textadv.utilita.funzionamento.azione;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.AnalizzaComando;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Aprire;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Dare;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Interazione;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Movimento;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Osservazione;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Prendere;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Usare;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.ExitException;

/**
 * Classe astratta che generalizza tutte le azioni, e fa si che possano specificare il suo metodo astratto active()
 * @author gioele
 *
 */
public abstract class Azione implements Iterable<Azione>{
	/**
	 * Lista delle classi concrete che permettono l'azione nel gioco, da scorrere per individuare l'azione
	 */
	public static final List<Azione> listaAzioni = List.of(	new Interazione(),
															new Aprire(),
															new Dare(),
															new Movimento(),
															new Osservazione(),
															new Prendere(),
															new Usare());

	private static final List<String> SET_COMANDI = List.of(Aprire.CON, Dare.A, Usare.SU, Prendere.DA);

	/**
	 * Comandi esterni al gioco per poter uscire o altro
	 */
	public static final Set<String> comandoEsterno = Set.of("exit", "apri tesoro");
	
	protected static String comandoRicevuto;
	private Set<String> setComandi;
	protected Predicate<Entita> predicate;

	public Azione(Set<String> setComandi, Predicate<Entita> predicate) {
		this.setComandi = setComandi;
		this.predicate = predicate;
	}
	
	/**
	 * Metodo che attiva l'azione a seconda del tipo ricevento almeno 1 entita
	 * @param entita = {@link Entita} ricevute da analizzare
	 */
	public abstract void active(Entita entita1, Entita... entita2) throws AzioneException, GiocatoreException;
	
	/**
	 * Metodo statico che restituisce la tipologia di azione da eseguire indiviuandola dalla prima parole del comando
	 * @param comando
	 * @return una classe concreta di Azione, null se non esiste
	 * @throws ExitException
	 */
	public static Azione getAzione(String comando) throws ExitException {
		comandoRicevuto = comando;
		
		if(comandoEsterno.contains(comando))
			throw new ExitException();
		
		for(Azione e : listaAzioni) {
			if(e.contains(comando))
				return e;
		}
		
		return null;
	}
	
	/**
	 * Metodo che controlla se la parola è contenuta nel set di parole per quella determinata azione
	 * @param parola
	 * @return {@link boolean}
	 */
	public boolean contains(String parola) {
		return setComandi.contains(parola);
	}
	
	/** 
	 * Metodo che preso in input la lista di comandi, a seconda di quale azione è da fare seleziona gli oggetti e li cattura
	 * @param comando
	 * @return
	 */
	public List<Entita> entita(List<String> comando, Set<Entita> set) {
		List<Entita> lista = new LinkedList<>();
		
		int secondaEntita =	SET_COMANDI.stream().map(x -> Collections.indexOfSubList(comando, List.of(x))).filter(x -> x !=-1).findAny().orElse(-1);
		
		if(secondaEntita != -1) {
			cerca(comando.subList(0, secondaEntita), set).ifPresent(x -> lista.add(x));
			cerca(comando.subList(secondaEntita, comando.size()), set).ifPresent(x -> lista.add(x));
		}
		else
			cerca(comando, set).ifPresent(x -> lista.add(x));
		
		return lista;
	}
	
	/**
	 * Metodo vero e proprio che cattura l'entita 
	 * @param parteComando
	 * @param set
	 * @return
	 */
	public Optional<Entita> cerca(List<String> parteComando, Set<Entita> set) {
		List<String> supporto = new ArrayList<>();
		Entita supportoEntita = null;
		int match = 0;
		
		for(Entita entita : set) {
			match = 0;
			if(predicate.test(entita)) {
				supporto = AnalizzaComando.stringInList(entita.getNome());
				match = supporto.size();
				if(Collections.indexOfSubList(parteComando, supporto) != -1) {
					if(match )
				}
					
				
			}
		}
		
		if(supportoEntita != null)
			return Optional.of(supportoEntita);
		
		return Optional.empty();
	}
	
	@Override
	public Iterator<Azione> iterator(){
		return listaAzioni.iterator();
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
