package entita.stanza;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import entita.Entita;
import entita.link.Link;
import entita.link.concreto.Libero;
import entita.oggetto.Oggetto;
import entita.personaggio.Personaggio;
import utilita.creazione.AnalizzaFile;
import utilita.creazione.eccezioni.concreto.EntitaException;
import utilita.creazione.interfaccia.Observer;
import utilita.enumerazioni.PuntoCardinale;

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
public class Stanza extends Entita implements Observer{
	private String DESCRIZIONE_STANZA;
	private Map<String, Oggetto> oggetti;
	private Map<String, Personaggio> personaggi;
	private Map<PuntoCardinale, Link> accessi;
	
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
		
		Link link = null;
		Stanza stanza = null;
		for(Map.Entry<PuntoCardinale, String> m : accessiString.entrySet()) {
			try {
				link = (Link) AnalizzaFile.convertitore(m.getValue());
			}
			catch(ClassCastException e) {
				stanza = (Stanza) AnalizzaFile.convertitore(m.getValue());
				link = new Libero(this, stanza);
			}

			accessi.put(m.getKey(), link);
		}
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
	
	public Oggetto getOggetto(String nome) {
		return oggetti.get(nome);
	}
	
	public Personaggio getPersonaggio(String nome) {
		return personaggi.get(nome);
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
