package it.uniroma1.textadv.entita.personaggio;

import it.uniroma1.textadv.entita.personaggio.concreto.Giocatore;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;

/**
 * Classe di personaggio: Umano
 * @author gioele
 *
 */
public abstract class Umano extends Personaggio {

	public Umano(String nome) {
		super(nome);
	}

	@Override
	public void interazione() {
		try {
			System.out.println("«Salve! " + Giocatore.getInstance().getNome() + " »");
		} catch (GiocatoreException e) {
			e.printStackTrace();
		}
	}
}
