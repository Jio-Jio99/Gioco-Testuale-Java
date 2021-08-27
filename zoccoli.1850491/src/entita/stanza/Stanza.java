package entita.stanza;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import entita.Entita;
import entita.link.Link;
import entita.oggetto.Oggetto;
import entita.personaggio.Personaggio;
import utilita.PuntoCardinale;
import utilita.eccezioni.concreto.EntitaException;
import utilita.interfaccie.AnalizzaFile;
import utilita.interfaccie.tag.Observer;

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
	private Set<Oggetto> oggetti;
	private Set<Personaggio> personaggi;
	private Map<PuntoCardinale,Link> accessi;
	
	//Corrispettivi in stringhe
	private Set<String> oggettiString;
	private Set<String> personaggiString;
	private Map<PuntoCardinale,String> accessiString;
	
	public Stanza(String nome, String descrizioneStanza, Set<String> oggettiString, Set<String> personaggiString, Map<PuntoCardinale,String> accessiString) {
		super(nome);
		this.DESCRIZIONE_STANZA = descrizioneStanza;
		this.oggetti = new HashSet<>();
		this.personaggi = new HashSet<>();
		this.accessi = new HashMap<>();
		
		//inizializzo gli insiemi delle stringhe
		this.oggettiString = oggettiString;
		this.personaggiString = personaggiString;
		this.accessiString = accessiString;
	}
	
	@Override
	public void converti() throws EntitaException {
		for(String s : oggettiString) 
			oggetti.add((Oggetto) AnalizzaFile.convertitore(s));
		
		for(String s : personaggiString)
			personaggi.add((Personaggio) AnalizzaFile.convertitore(s));
		
		for(Map.Entry<PuntoCardinale, String> m : accessiString.entrySet()) 
			accessi.put(m.getKey(), (Link) AnalizzaFile.convertitore(m.getValue()));
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
