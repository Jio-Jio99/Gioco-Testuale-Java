package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.oggetto.Contenitore;

/**
 * Classe che rappresenta il contenitore Cassetto
 * @author gioele
 *
 */
public class Cassetto extends Contenitore{

	public Cassetto(String nome) {
		super(nome);
		chiusoAChiave = false;
	}
}
