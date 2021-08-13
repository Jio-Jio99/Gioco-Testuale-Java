package entita.link.concreto;

import entita.link.Link;
import entita.oggetto.Oggetto;
import entita.stanza.Stanza;

public class Bus extends Link{

	public Bus(String nome, Stanza stanza1, Stanza stanza2) {
		super(nome, stanza1, stanza2);
	}
	
	@Override
	public void setStato(Oggetto oggetto) {

	}
}
