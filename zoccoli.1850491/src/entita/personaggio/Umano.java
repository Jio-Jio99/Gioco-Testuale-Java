package entita.personaggio;

import entita.personaggio.concreto.Giocatore;
import utilita.creazione.eccezioni.GiocatoreException;

public class Umano extends Personaggio {

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
