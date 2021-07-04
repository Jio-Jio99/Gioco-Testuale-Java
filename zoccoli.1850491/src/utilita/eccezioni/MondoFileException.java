package utilita.eccezioni;

/**
 * Eccezione per gestire evenutali errori dal caricamento del Mondo dal file .game:
 * <pre>
 * - Errori di formattazione file;
 * - Errori di collegamenti nel file,
 * - Errore del posizionamento di un entità del gioco (un stesso oggetto in più posti)
 * </pre>
 * All'interno della classe è presente la classe enumerativa {@link TipoErrore}, contenente i 3 tipi di errori individuati
 * @author gioele
 *
 */
public class MondoFileException extends Exception{
	private static final long serialVersionUID = 1L;
	private final TipoErrore tipologiaErrore;
	
	/**
	 * Classe enumerativa che indentifica i 3 tipi di errore da lanciare
	 * @see FORMATTAZIONE
	 * @see LINK
	 * @see POSIZIONE
	 * @author gioele
	 */
	public static enum TipoErrore{
		/**
		 * Errore sulla formattazione
		 */
		FORMATTAZIONE		{public String toString() {return "Attenzione! È presente un errore di formattazione nel file per il caricamento del mondo";}},
		/**
		 * Errore di collegamento tra stanze, ex: una stanza ha un collegamento, ma quello stesso non è presente nella stanza collegata
		 */
		LINK 				{public String toString() {return "Attenzione! È presente un errore di collegamento tra le stanze nel file per il caricamento del mondo";}},
		/**
		 * Errore sulla posizione degli oggetti, stesso oggetto in due posti diversi
		 */
		POSIZIONE 			{public String toString() {return "Attenzione! Nel file per il caricamento del mondo, uno stesso oggetto si trova in due posizioni diverse!";}};
		
		abstract public String toString();
	}
	
	public MondoFileException(TipoErrore tipologiaErrore) {
		super(tipologiaErrore.toString());
		
		this.tipologiaErrore = tipologiaErrore;
	}
	
	public MondoFileException() {
		this(TipoErrore.FORMATTAZIONE);
	}
	
	/**
	 * Metodo che restituisce il tipo di errore rilevato
	 * @return {@link TipoErrore}
	 */
	public TipoErrore getTipoErrore() {
		return tipologiaErrore;
	}
}
