package entita.oggetto;

import java.util.Objects;

import entita.Entita;
import utilita.eccezioni.concreto.EntitaException;
import utilita.interfaccie.tag.Inventario;
import utilita.interfaccie.tag.Observer;

public abstract class Oggetto extends Entita implements Inventario, Observer{
	
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
}
