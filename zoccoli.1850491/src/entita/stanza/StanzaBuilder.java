package entita.stanza;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import utilita.eccezioni.concreto.PuntoCardinaleException;
import utilita.enumerazioni.PuntoCardinale;

/**
 * Classe builder che costuisce la {@link Stanza}
 * @author gioele
 *
 */
public class StanzaBuilder {
	private String descrizione;
	private String nome;
	
	private Set<String> oggettiString;
	private Set<String> personaggiString;
	private Map<PuntoCardinale,String> accessiString;
	
	private StanzaBuilder(String nome, String descrizione) {
		this.nome = nome;
		this.descrizione = descrizione;
		oggettiString = new HashSet<>();
		personaggiString = new HashSet<>();
		accessiString = new HashMap<>();
	}
	
	/**
	 * Metodo che restituisce un'instanza della classe StanzaBuilder, prendendo i parametri obbligatori
	 * @param nome
	 * @param descrizione
	 * @return StanzaBuilder
	 */
	public static StanzaBuilder creaStanzaBuilder(String nome, String descrizione) {
		return new StanzaBuilder(nome, descrizione);
	}
	
	
	/**
	 * Metodo che crea il set di stringhe degli oggetti nella stanza
	 * @param oggetti
	 * @return StanzaBuilder
	 */
	public StanzaBuilder objects(String oggetti) {
		
		oggettiString = inserimento(oggetti);
		
		return this;
	}
	
	/**
	 * Metodo che crea il set di stringhe dei personaggi nella stanza
	 * @param personaggi
	 * @return StanzaBuilder
	 */
	public StanzaBuilder characters(String personaggi) {

		personaggiString = inserimento(personaggi);
		
		return this;
	}
	
	/**
	 * Metodo che crea la mappa<PuntoCardinale, String> con gli accessi nei posti
	 * @param accessi
	 * @return StanzaBuilder
	 */
	public StanzaBuilder links(String accessi) {
		List<String> preMappa = Arrays.asList(accessi.split(","));
		
		accessiString = preMappa.stream().map(x -> x.split(":")).collect(Collectors.toMap(x -> {
			
			try {
				return PuntoCardinale.getDirezione(x[0]);
			} catch (PuntoCardinaleException e) {
				e.printStackTrace();
			}
			return null;
			
		}, x -> x[1]));
		
		return this;
	}
	
	/**
	 * Metodo che ritorna la Stanza creata
	 * @return {@link Stanza}
	 */
	public Stanza build() {
		return new Stanza(nome, descrizione, oggettiString, personaggiString, accessiString);
	}
	
	
	//METODI PRIVATI DI SUPPORTO
	/**
	 * Metodo generale che crea un set di String, prendendo una stringa e splittandola sulla virgola, inserendo gli elementi in un set di String
	 * @param pattern
	 * @return Set<String>
	 */
	private Set<String> inserimento(String pattern) {
		Set<String> set = new HashSet<>();
		List<String> listaString = Arrays.asList(pattern.split(","));
		
		for(String s : listaString)
			set.add(s);
		
		return set;
	}
}