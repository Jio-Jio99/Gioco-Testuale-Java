package entita.link.concreto;

import entita.link.Link;
import entita.oggetto.Oggetto;
import entita.stanza.Stanza;

public class Botola extends Link {

	public Botola(String nome, Stanza stanza1, Stanza stanza2) {
		super(nome, stanza1, stanza2);
	}

	@Override
	public void setStato(Oggetto oggetto) {
		if(oggetto.getClass().equals("Vite".getClass()))
			aperto = true;
		else
			System.out.println("Errore");
	}

}
