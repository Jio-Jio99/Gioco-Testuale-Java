package entita.personaggio;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import entita.Entita;
import entita.oggetto.Oggetto;
import utilita.interfaccie.tag.Inventario;

/**
 * Ogni personaggio ha un nome. Un personaggio che dispone di un inventario di oggetti. Il
personaggio dispone di una serie di metodi che gli permettono di interagire con la stanza in
cui si trova e con gli altri personaggi presenti nella stanza.
 * @author gioele
 *
 */
public class Personaggio extends Entita {
	private Set<Inventario> inventario;

	public Personaggio(String nome, Set<Inventario> inventario) {
		super(nome);
		this.inventario = new HashSet<>();
		this.inventario.addAll(inventario);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null || !o.getClass().equals(getClass())) 
			return false;
		
		if(o == this)
			return true;
		
		Personaggio s = (Personaggio) o;
		
		return NOME.equals(s.NOME) &&
			   inventario.equals(s.inventario);
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(NOME);
	}

}
