package it.uniroma1.textadv.entita.link.concreto;

import it.uniroma1.textadv.entita.link.Link;
import it.uniroma1.textadv.entita.stanza.Stanza;

/**
 * Classe speciale, che rappresenta un collegamento diretto tra due stanze, non esistendo un link che le divide, esso è sempre aperto
 * e contiene come un link normale le due stanze che esso connette
 * @author gioele
 *
 */
public class Libero extends Link{
	public static final String accesso = "Accesso libero a ";
	private String nomeStanza;
	
	public Libero(String nome, Stanza stanza1, Stanza stanza2){
		super(accesso + nome, stanza1, stanza2);
		this.nomeStanza = nome;
		sblocca();
	}
	

	@Override
	public String guarda() {
		return "È un passaggio libero...  vedi l'altra parte... interessante! È " + nomeStanza;
	}
	
	/**
	 * Stampa il nome particolare del link Libero
	 */
	@Override
	public String toString() {
		return NOME;
	}

	/**
	 * Metodo in override per poter ricevere quale stanza connette
	 */
	@Override
	public String getNome() {
		return nomeStanza;
	}
}