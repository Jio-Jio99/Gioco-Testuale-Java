package entita;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Set;

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
	private Set<Stanza> stanze;
	private Stanza start;
	
	public Mondo(String nomeMondo, String descrizioneTestuale, Set<Stanza> stanze, Stanza start) {
		super(nomeMondo);
		DESCRIZIONE_TESTUALE = descrizioneTestuale;
		this.stanze = stanze;
		this.start = start;
	}
	
	public static Mondo fromFile(Path file) throws ErroreCaricamentoException {
		return AnalizzaFile.analizzaLista(FilesMethod.lettura(file));
	}
}
