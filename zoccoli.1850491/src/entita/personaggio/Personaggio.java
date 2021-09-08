package entita.personaggio;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import entita.Entita;
import entita.oggetto.Oggetto;
import entita.stanza.Stanza;
import utilita.creazione.AnalizzaFile;
import utilita.creazione.eccezioni.concreto.EntitaException;
import utilita.creazione.interfaccia.Observer;
import utilita.interfaccie.Inventario;

/**
 * Ogni personaggio ha un nome. Un personaggio che dispone di un inventario di oggetti. Il
personaggio dispone di una serie di metodi che gli permettono di interagire con la stanza in
cui si trova e con gli altri personaggi presenti nella stanza.
 * @author gioele
 *
 */
public abstract class Personaggio extends Entita implements Observer{
	protected Map<String, Inventario> inventario;
	private Set<String> inventarioString;
	protected Stanza posizione;
	
	public Personaggio(String nome) {
		super(nome);
		this.inventario = new HashMap<>();
	}
	
	
	//METODI SET
	public void setInventario(Set<String> inventarioString) {
		this.inventarioString = inventarioString;
	}
	
	public void removeOggetto(String o) {
		inventario.remove(o);
	}
	
	public void setPosizione(Stanza stanza) {
		posizione = stanza;
	}
	
	
	
	
	//METODI GET
	public Optional<Oggetto> getOggetto(String o) {
		Oggetto oggetto = (Oggetto) inventario.get(o);
		
		if(oggetto == null)
			return Optional.empty();
		
		removeOggetto(oggetto.getNome());
		
		return Optional.of(oggetto);
	}
	
	public Stanza getPosizione() {
		return posizione;
	}
	
	public Map<String, Inventario> getInventario(){
		return inventario;
	}
	//METODI PER INTERAZIONE 
	public abstract void interazione();
	
	public void prendi(Inventario o) {
		inventario.putIfAbsent(((Entita)o).getNome(), o);
		System.out.println(getNome() + " ti ringrazia!");
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
				inventario.put(s, (Inventario) AnalizzaFile.convertitore(s));
	}
}
