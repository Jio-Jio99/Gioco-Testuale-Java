package entita.personaggio;

import utilita.interfaccie.Inventario;

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
