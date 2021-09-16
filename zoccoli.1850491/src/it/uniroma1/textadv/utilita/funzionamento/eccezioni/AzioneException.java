package it.uniroma1.textadv.utilita.funzionamento.eccezioni;

/**
 * Classe di eccezioni sollevate dal comando
 * @author gioele
 */
public class AzioneException extends Exception {
	private static final long serialVersionUID = 1L;

	public AzioneException() {}
	
	
	public AzioneException(String errore) {
		super(errore);
	}
	
}
