package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.oggetto.Contenitore;

/**
 * Classe Scrivania, contenitore 
 * @author gioele
 *
 */
public class Scrivania extends Contenitore{

	public Scrivania(String nome) {
		super(nome);
		chiusoAChiave = false;
	}
}
