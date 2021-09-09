package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.oggetto.Contenitore;
import it.uniroma1.textadv.entita.oggetto.Oggetto;

public class Cassetto extends Contenitore{

	public Cassetto(String nome) {
		super(nome);
		aperto = true;
	}

	@Override
	public void apriCon(Oggetto e) {
		
	}

}
