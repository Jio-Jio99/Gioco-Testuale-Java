package entita.link.concreto;

import entita.link.Link;
import entita.oggetto.Oggetto;
import entita.stanza.Stanza;
import utilita.interfaccie.tag.Observable;

public class Bus extends Link{

	public Bus(String nome, String stanza1, String stanza2) {
		super(nome, stanza1, stanza2);
	}
}
