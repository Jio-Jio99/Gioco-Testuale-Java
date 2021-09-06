package entita.link.concreto;

import entita.link.Link;
import entita.oggetto.Oggetto;
import entita.stanza.Stanza;
import utilita.creazione.eccezioni.concreto.LinkFileException;
import utilita.creazione.interfaccia.Observable;

public class Botola extends Link {

	public Botola(String nome, String stanza1, String stanza2) throws LinkFileException {
		super(nome, stanza1, stanza2);
	}

	@Override
	public void apriCon(Oggetto o) {
		
	}
}
