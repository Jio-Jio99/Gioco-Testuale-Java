package entita.link.concreto;

import entita.link.Link;
import entita.oggetto.Oggetto;
import entita.stanza.Stanza;

public class Libero extends Link{
	public static final String NOME = "libero";

	public Libero(Stanza stanza1, Stanza stanza2){
		super(NOME, stanza1, stanza2);
		aperto = true;
	}

	@Override
	public void apriCon(Oggetto o) {}
}
