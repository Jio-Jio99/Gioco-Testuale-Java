package it.uniroma1.textadv.entita.stanza;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.PuntoCardinale;
import it.uniroma1.textadv.entita.interfaccia.Datore;
import it.uniroma1.textadv.entita.interfaccia.Description;
import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.entita.link.Link;
import it.uniroma1.textadv.entita.link.concreto.Libero;
import it.uniroma1.textadv.entita.oggetto.Contenitore;
import it.uniroma1.textadv.entita.oggetto.Oggetto;
import it.uniroma1.textadv.entita.personaggio.Personaggio;
import it.uniroma1.textadv.utilita.creazione.AnalizzaFile;
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.EntitaException;
import it.uniroma1.textadv.utilita.creazione.interfaccia.Observer;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.AccessoNonDisponibileException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.OggettoNonInStanzaException;

/**
 * La stanza è l’elemento base del mondo. Ogni stanza ha un nome, una descrizione testuale
 * e mantiene informazioni su:
 * <pre>
 *  - oggetti contenuti nella stanza;
 *  - personaggi e/o giocatore presenti nella stanza;
 *  - punti di accesso ad altre stanze (link).
 * </pre>
 * @author gioele
 */
public class Stanza extends Entita implements Observer, Description, Datore{
	private String DESCRIZIONE_STANZA;
	private Map<String, Oggetto> oggetti;
	private Map<String, Personaggio> personaggi;
	private Map<PuntoCardinale, Link> accessi;
	private Set<String> entita;
	private Map<String, Contenitore> entitaNascoste;
	
	//Corrispettivi in stringhe
	private Set<String> oggettiString;
	private Set<String> personaggiString;
	private Map<PuntoCardinale,String> accessiString;
	
	public Stanza(String nome, String descrizioneStanza, Set<String> oggettiString, Set<String> personaggiString, Map<PuntoCardinale,String> accessiString) {
		super(nome);
		this.DESCRIZIONE_STANZA = descrizioneStanza;
		this.oggetti = new HashMap<>();
		this.personaggi = new HashMap<>();
		this.accessi = new HashMap<>();
		entita = new HashSet<>();
		entitaNascoste = new HashMap<>();

		
		//inizializzo gli insiemi delle stringhe
		this.oggettiString = oggettiString;
		this.personaggiString = personaggiString;
		this.accessiString = accessiString;
	}
	
	@Override
	public void converti() throws EntitaException {
		Oggetto oggetto = null;
		
		if(oggettiString != null) {
			for(String s : oggettiString) {
				oggetto = (Oggetto) AnalizzaFile.convertitore(s);
				oggetti.put(s, oggetto);
				if(oggetto instanceof Contenitore)
					entita.add(s);
			}
		}
		
		Personaggio p = null;
		if(personaggiString != null) {
			for(String s : personaggiString) {
				p = (Personaggio) AnalizzaFile.convertitore(s);
				personaggi.put(s, p);
				p.setPosizione(this);
			}
		}
		
		Link l = null;
		Stanza stanza = null;
		for(Map.Entry<PuntoCardinale, String> m : accessiString.entrySet()) {
			try {
				l = (Link) AnalizzaFile.convertitore(m.getValue());
			}catch (ClassCastException e) {
				stanza = (Stanza) AnalizzaFile.convertitore(m.getValue());
				l = new Libero(stanza.getNome(), this, stanza);
			}
			accessi.put(m.getKey(), l);
		}
		
		entita.addAll(getNomi(personaggi.values()));
		entita.addAll(getNomi(accessi.values()));
		
		for(Oggetto og : oggetti.values()) {
			entita.add(og.getNome());
			
			if(og instanceof Contenitore ) {
				Contenitore i = (Contenitore) og;
				entitaNascoste.put(i.getOggettoString(), i);
			}
		}
	}
	
	public Contenitore entitaNascosta(String nome) {
		return entitaNascoste.get(nome);
	}
	
	@Override
	public String guarda() {
		return 	"Nome della stanza: " + getNome() +", " +
				DESCRIZIONE_STANZA + 
				(oggetti.isEmpty() ? "" : "\n-Oggetti in vista: " + oggetti.values().toString().replaceAll("[\\[\\]]", ""))+ 
				(personaggi.isEmpty() ? "" : "\n-Guarda chi c'è! " + personaggi.values().toString().replaceAll("[\\[\\]]", "")) + 
				"\n-Accessi: " + accessi.toString().replaceAll("[{}]"," ");
				
	}
	
	public Map<String, Personaggio> getPersonaggi(){
		return personaggi;
	}
	
	public Map<String, Oggetto> getInventario(){
		return oggetti;
	}
	
	public Map<PuntoCardinale, Link> getAccessi(){
		return accessi;
	}
	
	public Link getAccesso(PuntoCardinale p) throws AccessoNonDisponibileException {
		Link l = accessi.get(p);
		if(l == null)
			throw new AccessoNonDisponibileException();
		
		return l;
	}
	
	public boolean verificaAccessoLibero(String nomeAccesso) {
		return accessi.values().stream().anyMatch(x -> x.getNome().equals(nomeAccesso));
	}
	
	public Link getAccessoLibero(String nomeAccesso) throws AccessoNonDisponibileException {
		return accessi.values().stream().filter(x -> x instanceof Libero && x.getNome().equals(nomeAccesso)).findAny().orElseThrow(AccessoNonDisponibileException::new);
	}
	
	public Oggetto getOggetto(String nome) {
		Oggetto o = oggetti.get(nome);
		return o;
	}
	
	public Personaggio getPersonaggio(String nome) {
		return personaggi.get(nome);
	}
	
	public Set<String> getEntita(){
		return entita;
	}
	
	public void removeOggetto(String nome) {
		oggetti.remove(nome);
		entita.remove(nome);
	}
	
	public void removePersonaggio(String nome) {
		personaggi.remove(nome);
		entita.remove(nome);
	}
	
	public void aggiungiElemento(Oggetto oggetto){
		oggetti.put(oggetto.getNome(), oggetto);
		entita.add(oggetto.getNome());
	}
	
	public void aggingiElemento(Personaggio p) {
		personaggi.put(p.getNome(), p);
		entita.add(p.getNome());
	}
	
	
	public boolean getEntita(String nome) {
		for(String e : entita) 
			if(e.equals(nome))
				return true;
		
		return entitaNascoste.containsKey(nome);	
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null || !o.getClass().equals(getClass())) 
			return false;
		
		if(o == this)
			return true;
		
		Stanza s = (Stanza) o;
		
		return NOME.equals(s.NOME)	&& 
			   DESCRIZIONE_STANZA.equals(s.DESCRIZIONE_STANZA) &&
			   oggetti.equals(s.oggetti) && 
			   personaggi.equals(s.personaggi) && 
			   accessi.equals(s.accessi);
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(NOME, DESCRIZIONE_STANZA);
	}
	
	private Set<String> getNomi(Collection<? extends Entita> set){
		return set.stream().map(x -> x.getNome()).collect(Collectors.toSet());
	}

	@Override
	public Inventario dai(String nomeInventario) throws AzioneException {
		Inventario in = null;
		Oggetto og =  getOggetto(nomeInventario);
		Personaggio per = getPersonaggio(nomeInventario);
		
		if(og == null) {
			in = (Inventario) per;
			removePersonaggio(nomeInventario);
		}
		else{
			in = (Inventario) og;
			removeOggetto(nomeInventario);
		}
		
		if(in == null)
			throw new OggettoNonInStanzaException();
		
		return in;
	}
}
