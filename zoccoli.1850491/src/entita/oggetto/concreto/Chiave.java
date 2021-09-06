package entita.oggetto.concreto;


import entita.oggetto.Chiavistello;

public class Chiave extends Chiavistello{

	public Chiave(String nome) {
		super(nome);
	}
	
	@Override
	public String guarda() {
		return "È una " + getNome().toLowerCase();
	}
}