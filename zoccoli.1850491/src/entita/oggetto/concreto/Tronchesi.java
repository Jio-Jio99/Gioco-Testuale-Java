package entita.oggetto.concreto;

import entita.oggetto.Oggetto;

public class Tronchesi extends Oggetto{

	public Tronchesi(String nome) {
		super(nome);
	}
	
	
	@Override
	public String guarda() {
		return "È una" + getNome().toLowerCase();
	}
}
