package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.oggetto.Contenitore;
import it.uniroma1.textadv.entita.oggetto.Oggetto;
import it.uniroma1.textadv.utilita.interfaccie.Inventario;

public class Tesoro extends Contenitore implements Inventario {

	public Tesoro(String nome) {
		super(nome);
	}
	
	@Override
	public String guarda() {
		return "È IL TESOROOOOOO";
	}

	@Override
	public void apriCon(Oggetto e) {
		
	}
}
