package it.uniroma1.textadv.entita.personaggio;

import it.uniroma1.textadv.entita.interfaccia.Inventario;

public abstract class Animale extends Personaggio implements Inventario{
	protected String verso;
	
	public Animale(String nome) {
		super(nome);
	}
	
	@Override
	public void converti() {}
	
	@Override
	public void interazione() {
		System.out.println(verso);
	}
}
