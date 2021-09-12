package it.uniroma1.textadv.entita;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import it.uniroma1.textadv.entita.interfaccia.Description;
import it.uniroma1.textadv.entita.stanza.Stanza;
import it.uniroma1.textadv.utilita.creazione.AnalizzaFile;
import it.uniroma1.textadv.utilita.creazione.eccezioni.ErroreCaricamentoException;
import it.uniroma1.textadv.utilita.funzionamento.AnalizzaComando;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.StanzaNonPresenteException;
import it.uniroma1.textadv.utilita.interfaccieSupporto.FilesMethod;

/**
 * Il mondo è un insieme di stanze ed è dotato di un nome e una descrizione testuale 
 * @author gioele
 * @see #SalvaStato
 * @see #mondo 
 */
public class Mondo extends Entita implements Description{
	private final String DESCRIZIONE_TESTUALE;
	private Map<String, Stanza> stanze;
	private Set<Entita> entita;
	private Set<List<String>> entitaString;
	
	public Mondo(String nomeMondo, String descrizioneTestuale, Map<String,? super Stanza> stanze) {
		super(nomeMondo);
		DESCRIZIONE_TESTUALE = descrizioneTestuale;
		 
		entita = AnalizzaFile.getEntita();
		
		entitaString = getEntita().stream().map(x -> AnalizzaComando.stringInList(x.getNome())).collect(Collectors.toSet());
		
		this.stanze = stanze.values().stream().map(x -> (Stanza)x).collect(Collectors.toMap(x -> x.getNome(), Function.identity()));
	}
	
	/**
	 * Metodo che ritorna il nuovo mondo caricato e analizzato dal file dato
	 * @param file
	 * @return
	 * @throws ErroreCaricamentoException
	 */
	public static Mondo fromFile(Path file) throws ErroreCaricamentoException {
		return AnalizzaFile.analizzaLista(FilesMethod.lettura(file));
	}
	
	/**
	 * Overloading del metodo che ritorna il nuovo mondo, prendento invece di un Path la stringa della directory
	 * @param file
	 * @return
	 * @throws ErroreCaricamentoException
	 */
	public static Mondo fromFile(String file) throws ErroreCaricamentoException {
		return fromFile(Paths.get(file));
	}
	
	/**
	 * Metodo che dato in input un nome di una stanza, ne ritorna il suo riferimento se presente in questo mondo
	 * @param nomeStanza
	 * @return Stanza
	 * @throws StanzaNonPresenteException
	 */
	public Stanza getStanza(String nomeStanza) throws StanzaNonPresenteException{
		Stanza s = stanze.get(nomeStanza);
		
		if(s == null)
			throw new StanzaNonPresenteException();
		
		return s;
	}
	
	public Set<Entita> getEntita(){
		return entita;
	}
	
	public Set<List<String>> getEntitaString(){
		return entitaString;
	}
	
	
	@Override
	public String guarda() {
		return "\t\t\t" + getNome().toUpperCase() + "\n  " + DESCRIZIONE_TESTUALE;
	}
}