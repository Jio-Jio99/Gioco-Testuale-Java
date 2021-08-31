package entita.personaggio;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import entita.Entita;
import entita.stanza.Stanza;
import utilita.AnalizzaFile;
import utilita.eccezioni.concreto.EntitaException;
import utilita.interfaccie.tag.Inventario;
import utilita.interfaccie.tag.Observer;

/**
 * Ogni personaggio ha un nome. Un personaggio che dispone di un inventario di oggetti. Il
personaggio dispone di una serie di metodi che gli permettono di interagire con la stanza in
cui si trova e con gli altri personaggi presenti nella stanza.
 * @author gioele
 *
 */
public abstract class Personaggio extends Entita implements Observer{
	private Set<Inventario> inventario;
	private Set<String> inventarioString;
	protected Stanza posizione;
	
	public Personaggio(String nome) {
		super(nome);
		this.inventario = new HashSet<>();
	}
	
	public void setInventario(Set<String> inventarioString) {
		this.inventarioString = inventarioString;
	}
	
	
	public void setPosizione(Stanza stanza) {
		posizione = stanza;
	}
	
	public Stanza getPosizione(Stanza stanza) {
		return posizione;
	}
	
	public Set<Inventario> getInventario(){
		return inventario;
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

	@Override
	public void converti() throws EntitaException {
		if(inventarioString != null)
			for(String s : inventarioString)
				inventario.add((Inventario) AnalizzaFile.convertitore(s));
	}
	
	@Override
	public String toString() {
		return NOME + " " + inventario;
	}
}
