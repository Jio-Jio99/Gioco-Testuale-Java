package it.uniroma1.textadv.entita.link.concreto;

import it.uniroma1.textadv.entita.link.Link;
import it.uniroma1.textadv.entita.stanza.Stanza;

public class Libero extends Link{
	public static final String accesso = "Accesso libero a ";
	private String nomeStanza;
	
	public Libero(String nome, Stanza stanza1, Stanza stanza2){
		super(accesso + nome, stanza1, stanza2);
		this.nomeStanza = nome;
		sblocca();
	}
	
	@Override
	public String getNome() {
		return nomeStanza;
	}
	
	@Override
	public String guarda() {
		return "È un passaggio libero...  vedi l'altra parte... interessante! È " + nomeStanza;
	}
	
	@Override
	public String toString() {
		return NOME;
	}
}