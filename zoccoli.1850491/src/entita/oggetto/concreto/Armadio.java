package entita.oggetto.concreto;

import entita.oggetto.Contenitore;

public class Armadio extends Contenitore {

	public Armadio(String nome) {
		super(nome);
	}

	@Override
	public String guarda() {
		return "È un'" + getNome().toLowerCase();
	}
}
