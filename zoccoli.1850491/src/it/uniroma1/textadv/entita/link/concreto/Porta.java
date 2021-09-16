package it.uniroma1.textadv.entita.link.concreto;

import it.uniroma1.textadv.entita.link.Link;
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.LinkFileException;

/**
 * Classe che rappresenta la Porta, inizialmente non chiuse a chiave, poiché solo con il chiavistello verranno chiuse
 * @author gioele
 *
 */
public class Porta extends Link{

	public Porta(String nome, String stanza1, String stanza2) throws LinkFileException {
		super(nome, stanza1, stanza2);
		chiusoAChiave = false;
	}
	
	@Override
	public String guarda() {
		return "È una " + getNome().toLowerCase();
	}
}
