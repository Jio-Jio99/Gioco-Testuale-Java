package entita.personaggio;

import java.util.Objects;


public class Personaggio {

	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean equals(Object o) {
		if(o == null || !o.getClass().equals(getClass())) 
			return false;
		
		if(o == this)
			return true;
		
		Personaggio s = (Personaggio) o;
		
		return true; //da fare
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(0);
	}
}
