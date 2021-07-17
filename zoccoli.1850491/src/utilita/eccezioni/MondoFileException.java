package utilita.eccezioni;

/**
 * Classe astratta Eccezione per gestire evenutali errori dal caricamento del Mondo dal file .game.
 * Tipologia di eccezione gestite dalle classi concrete:
 * <pre>
 * - Errori di formattazione file;
 * - Errori di collegamenti nel file,
 * - Errore del posizionamento di un entità del gioco (un stesso oggetto in più posti)
 * </pre>
 * @author gioele
 *
 */
public abstract class MondoFileException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public MondoFileException(String tipoErrore) {
		super(tipoErrore);
	}
}