package entita.link.concreto;

import entita.link.Link;
import entita.oggetto.Oggetto;
import utilita.azione.interfaccia.Apribile;
import utilita.creazione.eccezioni.concreto.LinkFileException;

public class Teletrasporto extends Link implements Apribile{

	public Teletrasporto(String nome, String stanza1, String stanza2) throws LinkFileException {
		super(nome, stanza1, stanza2);
	}

	@Override
	public void apriCon(Oggetto e) {
		
	}

	@Override
	public String guarda() {
		return "È un teletrasporto! Caspita! La cosa si fa interessante";
	}
}
