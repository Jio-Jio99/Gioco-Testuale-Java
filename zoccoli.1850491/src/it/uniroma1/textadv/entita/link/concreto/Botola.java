package it.uniroma1.textadv.entita.link.concreto;

import it.uniroma1.textadv.entita.link.Link;
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.LinkFileException;

/**
 * Classe che rappresenta la Botola
 * @author gioele
 *
 */
public class Botola extends Link {

	public Botola(String nome, String stanza1, String stanza2) throws LinkFileException {
		super(nome, stanza1, stanza2);
		chiusoAChiave = true;
	}

	@Override
	public String guarda() {
		return "È una " + getNome().toLowerCase();
	}
	
	
}
