package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.oggetto.Contenitore;
import it.uniroma1.textadv.entita.oggetto.Oggetto;

public class Scrivania extends Contenitore{

	public Scrivania(String nome) {
		super(nome);
		aperto = true;
	}

	@Override
	public void apriCon(Oggetto e) {
		
	}
}
