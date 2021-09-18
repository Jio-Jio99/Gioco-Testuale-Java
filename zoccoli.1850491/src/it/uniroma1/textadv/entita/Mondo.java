package it.uniroma1.textadv.entita;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import it.uniroma1.textadv.entita.interfaccia.Description;
import it.uniroma1.textadv.entita.stanza.Stanza;
import it.uniroma1.textadv.utilita.creazione.AnalizzaFile;
import it.uniroma1.textadv.utilita.creazione.eccezioni.ErroreCaricamentoException;
import it.uniroma1.textadv.utilita.creazione.eccezioni.FileNonEsistenteOVuotoException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.EntitaNonDiQuestoMondoException;
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
	
	public Mondo(String nomeMondo, String descrizioneTestuale, Map<String,? super Stanza> stanze) {
		super(nomeMondo);
		DESCRIZIONE_TESTUALE = descrizioneTestuale;
		 
		entita = AnalizzaFile.getEntita();
		
		this.stanze = stanze.values().stream().map(x -> (Stanza)x).collect(Collectors.toMap(x -> x.getNome(), Function.identity()));
	}
	
	/**
	 * Metodo che ritorna il nuovo mondo caricato e analizzato dal file dato
	 * @param file
	 * @return
	 * @throws ErroreCaricamentoException
	 */
	public static Mondo fromFile(Path file) throws ErroreCaricamentoException {
		return AnalizzaFile.analizzaLista(FilesMethod.lettura(file).orElseThrow(FileNonEsistenteOVuotoException::new));
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
	 * @throws EntitaNonDiQuestoMondoException
	 */
	public Stanza getStanza(String nomeStanza) throws EntitaNonDiQuestoMondoException{
		Stanza s = stanze.get(nomeStanza);
		
		if(s == null)
			throw new EntitaNonDiQuestoMondoException();
		
		return s;
	}
	
	/**
	 * Metodo che ritorna il set delle entita create da file
	 * @return
	 */
	public Set<Entita> getEntita(){
		return entita;
	}
	
	
	@Override
	public String guarda() {
		return "\t\t\t" + getNome().toUpperCase() + "\n  " + DESCRIZIONE_TESTUALE;
	}
}