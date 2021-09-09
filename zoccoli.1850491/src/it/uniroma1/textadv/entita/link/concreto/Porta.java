package it.uniroma1.textadv.entita.link.concreto;

import it.uniroma1.textadv.entita.link.Link;
import it.uniroma1.textadv.entita.oggetto.Oggetto;
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.LinkFileException;

public class Porta extends Link{

	public Porta(String nome, String stanza1, String stanza2) throws LinkFileException {
		super(nome, stanza1, stanza2);
	}

	@Override
	public void apriCon(Oggetto e) {
		
	}
	
	@Override
	public String guarda() {
		return "È una " + getNome().toLowerCase();
	}
}