package entita.oggetto.concreto;

import entita.oggetto.Contenitore;
import utilita.interfaccie.Inventario;

public class Tesoro extends Contenitore implements Inventario {

	public Tesoro(String nome) {
		super(nome);
	}
	
	@Override
	public String guarda() {
		return "È IL TESOROOOOOO";
	}
}
