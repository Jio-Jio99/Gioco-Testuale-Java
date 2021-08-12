package utilita.eccezioni;

/**
 * Eccezione lanciata quando è presente un errore di formattazione nel file e che non segue il pattern <p>
 * Classe derivata da {@link MondoFileException}
 * @author gioele
 *
 */
public class FormattazioneFileException extends MondoFileException{
	public static final String ERRORE = "Attenzione! È presente un errore di formattazione nel file per il caricamento del mondo nella parte in ";
	
	
	public FormattazioneFileException(String errore) {
		super(ERRORE + errore);
	}
}