package it.uniroma1.textadv.utilita.funzionamento;

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
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.PuntoCardinaleException;
import it.uniroma1.textadv.utilita.enumerazioni.PuntoCardinale;
import it.uniroma1.textadv.utilita.funzionamento.azione.Azione;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Movimento;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.ComandoNonRiconosciutoException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.OggettoNonInStanzaException;

public abstract class AnalizzaComando {
	private static List<String> comando;

	/**
	 * 
	 * @param mondo
	 * @param comandoString
	 * @throws AzioneException
	 * @throws GiocatoreException
	 */
	public static void analizzaComando(Mondo mondo, String comandoString) throws AzioneException, GiocatoreException {
		Map<Integer, Entita> disponibili = new TreeMap<>();
		Stanza stanza = Giocatore.getInstance().getPosizione();
		comando = stringInList(comandoString);
		
		//prendo l'azione da fare tramite la prima parola
		Azione azione = Azione.getAzione(comando.get(0));
		
		if(azione == null && comando.size() != 1)
			throw new ComandoNonRiconosciutoException();
		
		disponibili = entitaPresenti(x -> {if(!(x instanceof Libero)) return x.getNome(); else return ((Libero)x).getNomeStanzaVisibile();}, stanza.getEntita());

		//AZIONE DA CONTROLLARE: MOVIMENTO, ESTRARRE DALLA STANZA IN QUALE DIREZIONE SI STA ANDANDO
		if(azione instanceof Movimento || (comando.size() == 1 && azione == null)) {
			PuntoCardinale p = null;
			for(String s : comando) {
				try {
					p = PuntoCardinale.getDirezione(s);
					break;
				} catch (PuntoCardinaleException e1) {continue;}
			}
			if(p != null) 
				disponibili.put(-1, stanza.getAccesso(p));
			else if(azione == null ) 
				throw new ComandoNonRiconosciutoException();
			if(azione == null)
				azione = new Movimento();			
		}	

		if(comando.size() > 1) {
			Map<Integer, Entita> entitaInComando = entitaPresenti(x -> x.getNome(), mondo.getEntita());

			if(!disponibili.values().containsAll(entitaInComando.values()))
				throw new OggettoNonInStanzaException();
		}
		if(disponibili.size() > 2)
			throw new AzioneException("Ehm... ci sono troppi oggetti con cui dovrei lavorare in questo comando... potresti evitare di metterne così tanti?");
		
		azione.active(disponibili.entrySet().stream().sorted().map(x -> x.getValue()).toArray(Entita[]::new));
	}
	
	/**
	 * Metodo che trasforma una stringa nella lista di parole contenute
	 * @param stringa 
	 * @return
	 */
	private static List<String> stringInList(String stringa){
		return Arrays.asList(stringa.split(" ")).stream().map(String::strip).filter(Predicate.not(String::isBlank)).collect(Collectors.toList());
	}
	
	
	private static Map<Integer, Entita> entitaPresenti(Function<Entita,String> funzione, Set<Entita> setEntita){
		Map<Integer, Entita> lista = new TreeMap<>();
		List<String> supporto;
		int i = -1;
		
		for(Entita e : setEntita) {
			supporto = stringInList(funzione.apply(e));
			i = Collections.indexOfSubList(comando, supporto);
			if(i != -1 && !(e instanceof Stanza))
				lista.put(i,e);
		}
		
		return lista;
	}
}