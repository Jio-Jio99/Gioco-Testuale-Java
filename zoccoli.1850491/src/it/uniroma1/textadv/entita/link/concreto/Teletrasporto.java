package it.uniroma1.textadv.entita.link.concreto;

import it.uniroma1.textadv.entita.link.Link;
import it.uniroma1.textadv.entita.personaggio.concreto.Giocatore;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.LinkFileException;

public class Teletrasporto extends Link{

	public Teletrasporto(String nome, String stanza1, String stanza2) throws LinkFileException {
		super(nome, stanza1, stanza2);
	}

	@Override
	public String guarda() {
		return "È un teletrasporto! Caspita! La cosa si fa interessante";
	}
	
	@Override
	public void sblocca() {
		try {
			chiusoAChiave = true;
			aperto = true;
			passaggio(Giocatore.getInstance());
		} catch (GiocatoreException e) {
			e.printStackTrace();
		}
	}
}
