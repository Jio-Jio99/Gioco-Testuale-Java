package it.uniroma1.textadv.entita.link;

import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.LinkFileException;

/**
 * SottoClasse di Link che rappresenta i Mezzi di Trasporto
 * @author gioele
 *
 */
public abstract class MezzoDiTrasporto extends Link {
	
	public MezzoDiTrasporto(String nome, String stanza1, String stanza2) throws LinkFileException {
		super(nome, stanza1, stanza2);
	}
	
	@Override
	public String toString() {
		return getNome() + (aperto ?  " disponibile" : " spento");
	}
}