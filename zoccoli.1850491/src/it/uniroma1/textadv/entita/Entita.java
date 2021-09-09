package it.uniroma1.textadv.entita;

public abstract class Entita {
	protected final String NOME;
	
	public Entita(String nome) {
		this.NOME = nome;
	}
	
	public String getNome() {
		return NOME;
	}
	
	@Override 
	public String toString() {
		return getNome();
	}
}
