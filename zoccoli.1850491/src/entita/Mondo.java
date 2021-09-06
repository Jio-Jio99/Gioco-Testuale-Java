package entita;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import entita.stanza.Stanza;
import utilita.creazione.AnalizzaFile;
import utilita.creazione.eccezioni.ErroreCaricamentoException;
import utilita.interfaccie.FilesMethod;

/**
 * Il mondo è un insieme di stanze ed è dotato di un nome e una descrizione testuale 
 * @author gioele
 * @see #SalvaStato
 * @see #mondo 
 */
public class Mondo extends Entita{
	private final String DESCRIZIONE_TESTUALE;
	private Map<String, Stanza> stanze;
	
	public Mondo(String nomeMondo, String descrizioneTestuale, Map<String,? super Stanza> stanze) {
		super(nomeMondo);
		DESCRIZIONE_TESTUALE = descrizioneTestuale;
		
		this.stanze = stanze.values().stream().map(x -> (Stanza)x).collect(Collectors.toMap(x -> x.getNome(), Function.identity()));
	}
	
	public static Mondo fromFile(Path file) throws ErroreCaricamentoException {
		return AnalizzaFile.analizzaLista(FilesMethod.lettura(file));
	}
	
	public static Mondo fromFile(String file) throws ErroreCaricamentoException {
		return fromFile(Paths.get(file));
	}
}