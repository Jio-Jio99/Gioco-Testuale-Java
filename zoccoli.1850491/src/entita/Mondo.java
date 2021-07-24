package entita;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import entita.stanza.Stanza;

/**
 * Il mondo è un insieme di stanze ed è dotato di un nome e una descrizione testuale 
 * @author gioele
 * @see #SalvaStato
 * @see #mondo 
 */
public class Mondo {
	private final String NOME_MONDO;
	private final String DESCRIZIONE_TESTUALE;
	private Set<Stanza> stanze;
	
	public Mondo(String nomeMondo, String descrizioneTestuale) {
		NOME_MONDO = nomeMondo;
		DESCRIZIONE_TESTUALE = descrizioneTestuale;
		stanze = new HashSet<>();
	}
	
	
	/**
	 * Metodo che attiva il gioco
	 */
	public static void play() {
		
	}
	
	public static void fromFile(Path file) {
		
	}

}
