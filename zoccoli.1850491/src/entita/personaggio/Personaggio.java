package entita.personaggio;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import entita.Entita;
import entita.oggetto.Oggetto;
import entita.stanza.Stanza;
import utilita.creazione.AnalizzaFile;
import utilita.creazione.eccezioni.concreto.EntitaException;
import utilita.creazione.interfaccia.Inventario;
import utilita.creazione.interfaccia.Observer;

/**
 * Ogni personaggio ha un nome. Un personaggio che dispone di un inventario di oggetti. Il
personaggio dispone di una serie di metodi che gli permettono di interagire con la stanza in
cui si trova e con gli altri personaggi presenti nella stanza.
 * @author gioele
 *
 */
public abstract class Personaggio extends Entita implements Observer{
	protected Set<Inventario> inventario;
	private Set<String> inventarioString;
	protected Stanza posizione;
	
	public Personaggio(String nome) {
		super(nome);
		this.inventario = new HashSet<>();
	}
	
	
	//METODI SET
	public void setInventario(Set<String> inventarioString) {
		this.inventarioString = inventarioString;
	}
	
	public void removeOggetto(Inventario o) {
		inventario.remove(o);
	}
	
	public void setPosizione(Stanza stanza) {
		posizione = stanza;
	}
	
	
	
	
	//METODI GET
	public Optional<Oggetto> getOggetto(String o) {
		Oggetto oggetto = (Oggetto) inventario.stream().filter(x -> ((Entita)x).getNome().equals(o)).findAny().orElse(null);
		
		if(oggetto == null)
			return Optional.empty();
		
		removeOggetto(oggetto);
		
		return Optional.of(oggetto);
	}
	
	public boolean oggettoPresente(String o) {
		return inventario.stream().anyMatch(x -> ((Entita)x).getNome().equals(o));
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
}
