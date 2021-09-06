package entita.oggetto.concreto;

import entita.oggetto.Contenitore;

public class Scrivania extends Contenitore{

	public Scrivania(String nome) {
		super(nome);
	}

	
	@Override
	public String guarda() {
		return "È una " + getNome().toLowerCase();
	}
}
