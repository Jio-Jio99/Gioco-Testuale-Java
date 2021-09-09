package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.entita.oggetto.Contenitore;

public class Tesoro extends Contenitore implements Inventario {

	public Tesoro(String nome) {
		super(nome);
		chiusoConChiave = false;
	}
	
	@Override
	public String guarda() {
		return "È IL TESOROOOOOO";
	}
}
