package it.uniroma1.textadv.entita.oggetto;

import java.util.Objects;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.interfaccia.Description;
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.EntitaException;
import it.uniroma1.textadv.utilita.creazione.interfaccia.Observer;

/**
 * Classe che rappresenta tutte le entita inanimate del mondo con particolari funzioni:
 *<pre>
 *- {@link Chiavistello}
 *- {@link Contenitore}
 *</pre>
 * @author gioele
 *
 */
public abstract class Oggetto extends Entita implements Observer, Description{
	
	public Oggetto(String nome) {
		super(nome);
	}
	
	
	@Override
	public boolean equals(Object o) {
		if(o == null || !o.getClass().equals(getClass())) 
			return false;
		
		if(o == this)
			return true;
		
		Oggetto s = (Oggetto) o;
		
		return NOME.equals(s.NOME);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(NOME);
	}
	
	@Override
	public String guarda() {
		return "È un " +  getNome().toLowerCase();
	}
	
	@Override
	/**
	 * Solo i contenitori hanno bisogno di conversioni, altrimenti non fa nulla
	 */
	public void converti() throws EntitaException{}
}
