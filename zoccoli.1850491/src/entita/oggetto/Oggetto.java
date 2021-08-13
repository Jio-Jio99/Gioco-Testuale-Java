package entita.oggetto;

import java.util.Objects;

import entita.Entita;

public abstract class Oggetto extends Entita{
	
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
}
