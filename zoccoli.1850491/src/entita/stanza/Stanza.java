package entita.stanza;

import java.util.Set;

import entita.oggetto.Oggetto;
import entita.personaggio.Personaggio;

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
public class Stanza {
	private final String NOME;
	private final String DESCRIZIONE_STANZA;
	private Set<Oggetto> oggetti;
	private Set<Personaggio> personaggi;
//	private Set<Link> accessi;
	
	public Stanza(String nome, String descrizioneStanza) {
		this.NOME = nome;
		this.DESCRIZIONE_STANZA = descrizioneStanza;
	}
}
