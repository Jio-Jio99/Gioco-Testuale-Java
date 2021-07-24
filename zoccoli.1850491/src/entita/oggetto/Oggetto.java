package entita.oggetto;

import java.util.Objects;

abstract public class Oggetto {

	
	
	
	
	
	
	
	
	@Override
	public boolean equals(Object o) {
		if(o == null || !o.getClass().equals(getClass())) 
			return false;
		
		if(o == this)
			return true;
		
		Oggetto s = (Oggetto) o;
		
		return true; //da fare
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(0);
	}
}
