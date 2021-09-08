package entita.personaggio.concreto;

import entita.personaggio.Umano;

public class Guardiano extends Umano {

	public Guardiano(String nome) {
		super(nome);
	}
	
	@Override
	public void interazione() {
		System.out.println("Ti guarda dall'alto in basso...\n\t\t«TU... NON PUOI... PASSARE!»");
	}
}
