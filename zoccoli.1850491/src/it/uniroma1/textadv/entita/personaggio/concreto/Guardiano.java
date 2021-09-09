package it.uniroma1.textadv.entita.personaggio.concreto;

import it.uniroma1.textadv.entita.personaggio.Umano;

public class Guardiano extends Umano {

	public Guardiano(String nome) {
		super(nome);
	}
	
	@Override
	public void interazione() {
		System.out.println("Ti guarda dall'alto in basso...\n\t\t«TU... NON PUOI... PASSARE!»");
	}
}
