package entita.link.concreto;

import entita.link.MezzoDiTrasporto;
import entita.oggetto.Oggetto;
import utilita.creazione.eccezioni.concreto.LinkFileException;

public class Bus extends MezzoDiTrasporto{

	public Bus(String nome, String stanza1, String stanza2) throws LinkFileException {
		super(nome, stanza1, stanza2);
	}

	@Override
	public void apriCon(Oggetto o) {
		// TODO Auto-generated method stub
		
	}
}
