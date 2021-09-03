package entita.link.concreto;

import entita.link.Link;
import entita.oggetto.Oggetto;
import entita.stanza.Stanza;
import utilita.creazione.interfaccie.Observable;

public class Porta extends Link {

	public Porta(String nome, String stanza1, String stanza2) {
		super(nome, stanza1, stanza2);
	}

}
