package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.oggetto.Contenitore;

public class Cassetto extends Contenitore{

	public Cassetto(String nome) {
		super(nome);
		chiusoAChiave = false;
	}
}
