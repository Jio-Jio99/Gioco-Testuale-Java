package it.uniroma1.textadv.utilita.creazione.eccezioni;

/**
 * Classe di Eccezioni per il giocatore
 * @author gioele
 *
 */
public abstract class GiocatoreException extends ErroreCaricamentoException{
	private static final long serialVersionUID = 1L;

	public GiocatoreException(String nome) {
		super(nome);
	}
}
