package entita;

import java.nio.file.Path;
import java.util.Map;

import entita.stanza.Stanza;
import utilita.creazione.AnalizzaFile;
import utilita.creazione.eccezioni.ErroreCaricamentoException;
import utilita.creazione.interfaccia.FilesMethod;

/**
 * Il mondo è un insieme di stanze ed è dotato di un nome e una descrizione testuale 
 * @author gioele
 * @see #SalvaStato
 * @see #mondo 
 */
public class Mondo extends Entita{
	private final String DESCRIZIONE_TESTUALE;
	private Map<String,? super Stanza> stanze;
	private Stanza start;
	
	public Mondo(String nomeMondo, String descrizioneTestuale, Map<String,? super Stanza> stanze, Stanza start) {
		super(nomeMondo);
		DESCRIZIONE_TESTUALE = descrizioneTestuale;
		this.stanze = stanze;
		this.start = start;
	}
	
	public static Mondo fromFile(Path file) throws ErroreCaricamentoException {
		return AnalizzaFile.analizzaLista(FilesMethod.lettura(file));
	}
}
