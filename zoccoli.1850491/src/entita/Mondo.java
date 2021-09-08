package entita;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import entita.stanza.Stanza;
import utilita.azione.interfaccia.Description;
import utilita.creazione.AnalizzaFile;
import utilita.creazione.eccezioni.ErroreCaricamentoException;
import utilita.interfaccie.FilesMethod;

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
	
	public Set<Stanza> getStanze(){
		return stanze.values().stream().collect(Collectors.toSet());
	}
	
	public static Mondo fromFile(Path file) throws ErroreCaricamentoException {
		return AnalizzaFile.analizzaLista(FilesMethod.lettura(file));
	}
	
	public static Mondo fromFile(String file) throws ErroreCaricamentoException {
		return fromFile(Paths.get(file));
	}
	
	public Set<Entita> getEntita(){
		return entita;
	}
	
	
	@Override
	public String guarda() {
		return getNome() + " " + DESCRIZIONE_TESTUALE;
	}
}