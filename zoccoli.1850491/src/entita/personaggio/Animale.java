package entita.personaggio;

import utilita.creazione.interfaccia.Inventario;

public abstract class Animale extends Personaggio implements Inventario{

	public Animale(String nome) {
		super(nome);
	}
	
	@Override
	public void converti() {}
}
