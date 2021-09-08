package entita.link.concreto;

import entita.link.MezzoDiTrasporto;
import utilita.creazione.eccezioni.concreto.LinkFileException;

public class Bus extends MezzoDiTrasporto{

	public Bus(String nome, String stanza1, String stanza2) throws LinkFileException {
		super(nome, stanza1, stanza2);
		aperto = true;
	}
	
	
	@Override
	public String guarda() {
		return "È un bus, " + getNome().toLowerCase();
	}
}
