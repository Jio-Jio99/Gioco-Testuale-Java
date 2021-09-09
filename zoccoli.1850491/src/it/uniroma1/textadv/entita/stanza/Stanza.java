package it.uniroma1.textadv.entita.stanza;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.PuntoCardinale;
import it.uniroma1.textadv.entita.interfaccia.Description;
import it.uniroma1.textadv.entita.link.Link;
import it.uniroma1.textadv.entita.link.concreto.Libero;
import it.uniroma1.textadv.entita.oggetto.Oggetto;
import it.uniroma1.textadv.entita.personaggio.Personaggio;
import it.uniroma1.textadv.utilita.creazione.AnalizzaFile;
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.EntitaException;
import it.uniroma1.textadv.utilita.creazione.interfaccia.Observer;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.AccessoNonDisponibileException;

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
public class Stanza extends Entita implements Observer, Description{
	private String DESCRIZIONE_STANZA;
	private Map<String, Oggetto> oggetti;
	private Map<String, Personaggio> personaggi;
	private Map<PuntoCardinale, Link> accessi;
	private Set<Entita> entita;
	
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
		
		//inizializzo gli insiemi delle stringhe
		this.oggettiString = oggettiString;
		this.personaggiString = personaggiString;
		this.accessiString = accessiString;
	}
	
	@Override
	public void converti() throws EntitaException {
		
		if(oggettiString != null)
			for(String s : oggettiString) 
				oggetti.put(s, (Oggetto) AnalizzaFile.convertitore(s));
				
		if(personaggiString != null) 
			for(String s : personaggiString) 
				personaggi.put(s, (Personaggio) AnalizzaFile.convertitore(s));
		
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
		
		entita.addAll(oggetti.values());
		entita.addAll(accessi.values());
		entita.addAll(personaggi.values());
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
	
	public Oggetto getOggetto(String nome) {
		return oggetti.get(nome);
	}
	
	public Personaggio getPersonaggio(String nome) {
		return personaggi.get(nome);
	}
	
	public Set<Entita> getEntita(){
		return entita;
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
}
