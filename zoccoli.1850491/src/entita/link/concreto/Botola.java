package entita.link.concreto;

import entita.link.Link;
import entita.oggetto.Oggetto;
import utilita.creazione.eccezioni.concreto.LinkFileException;

public class Botola extends Link {

	public Botola(String nome, String stanza1, String stanza2) throws LinkFileException {
		super(nome, stanza1, stanza2);
	}

	@Override
	public void apriCon(Oggetto o) {
		
	}

	@Override
	public String guarda() {
		return "È una " + getNome().toLowerCase();
	}
}
