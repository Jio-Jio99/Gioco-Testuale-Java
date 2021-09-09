package it.uniroma1.textadv.entita.oggetto;

import java.util.Objects;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.EntitaException;
import it.uniroma1.textadv.utilita.creazione.interfaccia.Observer;
import it.uniroma1.textadv.utilita.funzionamento.interfaccia.Description;
import it.uniroma1.textadv.utilita.interfaccie.Inventario;

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
	public void converti() throws EntitaException{}
	
	@Override
	public int hashCode() {
		return Objects.hash(NOME);
	}
	
	@Override
	public String guarda() {
		return "È un " +  getNome().toLowerCase();
	}
}
