package it.uniroma1.textadv.entita.oggetto.concreto;


import it.uniroma1.textadv.entita.oggetto.Chiavistello;

/**
 * Classe che rappresenta la chiave
 * @author gioele
 *
 */
public class Chiave extends Chiavistello{

	public Chiave(String nome) {
		super(nome);
	}
	
	@Override
	public String guarda() {
		return "È una " + getNome().toLowerCase();
	}
}