package it.uniroma1.textadv.entita.personaggio;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.interfaccia.Datore;
import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.entita.stanza.Stanza;
import it.uniroma1.textadv.utilita.creazione.AnalizzaFile;
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.EntitaException;
import it.uniroma1.textadv.utilita.creazione.interfaccia.Observer;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.OggettoNonInInventarioException;

/**
 * Classe astratta che rappresenta tutti i personaggi del gioco, con un possibile inventario, e un modo di interagiere con il giocatore 
 * @author gioele
 *
 */
public abstract class Personaggio extends Entita implements Observer, Datore{
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
	
	public void removeOggetto(Inventario o) {
		inventario.remove(((Entita)o).getNome());
	}
	
	public void setPosizione(Stanza stanza) {
		posizione = stanza;
	}
	
	
	//METODI GET
	/**
	 * Metodo che restituisce l'oggetto richiesto se prensente, e successivamente lo rimuove
	 * @param o
	 * @return
	 */
	public Optional<Inventario> getOggetto(String o) {
		Inventario oggetto = inventario.get(o);
		
		if(oggetto == null)
			return Optional.empty();
		
		removeOggetto(oggetto);
			
		posizione.removeOggetto(o);
		
		return Optional.of(oggetto);
	}
	
	public Stanza getPosizione() {
		return posizione;
	}
	
	public Map<String, Inventario> getInventario(){
		return inventario;
	}
	
	
	//METODI DI VERIFICA
	/**
	 * Metodo che controlla se l'oggetto è presente
	 * @param nomeOggetto
	 * @return
	 */
	public boolean containsOggetto(String nomeOggetto) {
		return inventario.containsKey(nomeOggetto);
	}
	
	//METODI PER INTERAZIONE 
	/**
	 * Metodo che permette l'interazione tra i personaggi e il giocatore
	 */
	public abstract void interazione();
	
	/**
	 * Metodo per prendere un oggetto e inserirlo nell'inventario
	 * @param o
	 */
	public void prendi(Inventario o) {
		inventario.putIfAbsent(((Entita)o).getNome(), o);
	}
	
	
	//METODI IN OVERRIDE
	@Override 
	public Inventario dai(String nomeInventario) throws AzioneException{
		return getOggetto(nomeInventario).orElseThrow(OggettoNonInInventarioException::new);
	}
	
	@Override 
	public void daiA(String nomeInventario, Personaggio p) throws AzioneException{
		Inventario o = (Inventario) getOggetto(nomeInventario).orElseThrow(OggettoNonInInventarioException::new);
		p.prendi(o);
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
	
	@Override
	public String toString() {
		return Character.toUpperCase(getNome().charAt(0)) + getNome().substring(1);
	}
}
