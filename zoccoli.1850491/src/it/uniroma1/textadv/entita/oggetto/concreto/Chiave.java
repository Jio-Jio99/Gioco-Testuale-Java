package it.uniroma1.textadv.entita.oggetto.concreto;


import it.uniroma1.textadv.entita.oggetto.Chiavistello;

public class Chiave extends Chiavistello{

	public Chiave(String nome) {
		super(nome);
	}
	
	@Override
	public String guarda() {
		return "È una " + getNome().toLowerCase();
	}
}