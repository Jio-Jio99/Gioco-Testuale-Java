package entita.link.concreto;

import entita.link.Link;
import entita.oggetto.Oggetto;
import entita.stanza.Stanza;

public class Teletrasporto extends Link {

	public Teletrasporto(String nome, Stanza stanza1, Stanza stanza2) {
		super(nome, stanza1, stanza2);
	}

	@Override
	public void setStato(Oggetto oggetto) {
		// TODO Auto-generated method stub
		
	}

}
