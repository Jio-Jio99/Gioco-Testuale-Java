package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.oggetto.Oggetto;

public class Libro extends Oggetto{
	
	public Libro(String nome) {
		super(nome);
	}
	
	
	@Override
	public String guarda() {
		return "È un vecchio libro ingiallito... con scritte in " + NOME.replace("libro", "");
	}
}
