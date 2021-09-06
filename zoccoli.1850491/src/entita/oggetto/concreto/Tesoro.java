package entita.oggetto.concreto;

import entita.oggetto.Contenitore;

public class Tesoro extends Contenitore {

	public Tesoro(String nome) {
		super(nome);
	}
	
	@Override
	public String guarda() {
		return "È IL TESOROOOOOO";
	}
}
