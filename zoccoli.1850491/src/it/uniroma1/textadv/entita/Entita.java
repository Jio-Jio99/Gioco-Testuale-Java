package it.uniroma1.textadv.entita;

/**
 * Classe astratta per eccellenza, rappresenta tutti gli oggetti, i personaggi ecc... del mondo caricato  e ne salva il nome
 * @author gioele
 *
 */
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
