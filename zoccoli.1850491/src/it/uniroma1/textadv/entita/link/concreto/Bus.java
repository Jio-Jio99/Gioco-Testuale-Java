package it.uniroma1.textadv.entita.link.concreto;

import it.uniroma1.textadv.entita.link.MezzoDiTrasporto;
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.LinkFileException;

/**
 * CLasse che rappresenta il Mezzo di Trasporto Bus
 * @author gioele
 *
 */
public class Bus extends MezzoDiTrasporto{

	public Bus(String nome, String stanza1, String stanza2) throws LinkFileException {
		super(nome, stanza1, stanza2);
		sblocca();
	}
	
	
	@Override
	public String guarda() {
		return "È un bus, " + getNome().toLowerCase();
	}
}
