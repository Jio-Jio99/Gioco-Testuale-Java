package entita.oggetto.concreto;

import entita.oggetto.Chiavistello;

public class Vite extends Chiavistello{

	public Vite(String nome) {
		super(nome);
	}
	
	
	@Override
	public String guarda() {
		return "È una " + getNome().toLowerCase();
	}
}
