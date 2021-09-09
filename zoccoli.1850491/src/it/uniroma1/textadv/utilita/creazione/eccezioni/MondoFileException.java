package it.uniroma1.textadv.utilita.creazione.eccezioni;

import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.EntitaException;
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.FormattazioneFileException;
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.LinkFileException;
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.PosizioneFileException;

/**
 * Classe astratta Eccezione per gestire evenutali errori dal caricamento del Mondo dal file .game.
 * Tipologia di eccezione gestite dalle classi concrete:
 * <pre>
 * - Errori di formattazione file, {@link FormattazioneFileException}
 * - Errori di collegamenti nel file, {@link LinkFileException}
 * - Errore del posizionamento di un entità del gioco (un stesso oggetto in più posti), {@link PosizioneFileException}
 * - Entita non esistente, {@link EntitaException}
 * </pre>
 * @author gioele
 *
 */
public abstract class MondoFileException extends ErroreCaricamentoException{
	private static final long serialVersionUID = 1L;
	
	public MondoFileException(String tipoErrore) {
		super(tipoErrore);
	}
}