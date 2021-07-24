package entita.link;

import java.util.Objects;

public class Link {
	
	
	
	
	
	
	
	

	@Override
	public boolean equals(Object o) {
		if(o == null || !o.getClass().equals(getClass())) 
			return false;
		
		if(o == this)
			return true;
		
		Link s = (Link) o;
		
		return true; //da fare
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(0);
	}
}
