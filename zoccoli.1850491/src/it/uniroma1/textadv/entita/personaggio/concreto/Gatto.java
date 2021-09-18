package it.uniroma1.textadv.entita.personaggio.concreto;

import it.uniroma1.textadv.entita.personaggio.Animale;

public class Gatto extends Animale {

	public Gatto(String nome) {
		super(nome);
		verso = "frrrrr.... ";
	}
	
	@Override
	public String guarda() {
		return "... ti guarda con due occhioniiiii";
	}
}
