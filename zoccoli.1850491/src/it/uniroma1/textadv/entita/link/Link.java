package it.uniroma1.textadv.entita.link;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.interfaccia.Apribile;
import it.uniroma1.textadv.entita.interfaccia.Description;
import it.uniroma1.textadv.entita.personaggio.Personaggio;
import it.uniroma1.textadv.entita.stanza.Stanza;
import it.uniroma1.textadv.utilita.creazione.AnalizzaFile;
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.EntitaException;
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.LinkFileException;
import it.uniroma1.textadv.utilita.creazione.interfaccia.Observer;

/**
 * Classe astratta dei link di collegamento delle varie stanze, contente:
 * <pre>
 *  - Nome
 *  - un set delle due stanze collegate da questo link
 * 
 * @author gioele
 */
public abstract class Link extends Entita implements Observer, Description, Apribile{
	protected boolean aperto;
	private Set<Stanza> collegamento;
	private List<String> nomeStanze;
	
	private Link(String nome) {
		super(nome);
		collegamento = new HashSet<>();
	}
	
	/**
	 * Costruttore del Link con gi� presenti le stanze
	 * @param nome
	 * @param stanza1
	 * @param stanza2
	 * @throws LinkFileException 
	 */
	public Link(String nome, Stanza stanza1, Stanza stanza2){
		this(nome);
		collegamento = Set.of(stanza1, stanza2);
	}
	
	/**
	 * Costruttore che riceve due stringhe, non due stanze, e di conseguenza ha bisogno dell'Observable
	 * @param nome
	 * @param stanza1
	 * @param stanza2
	 * @throws LinkFileException 
	 */
	public Link(String nome, String stanza1, String stanza2) throws LinkFileException {
		this(nome);
		
		if(stanza1.equals(stanza2))
			throw new LinkFileException(getNome());
		
		nomeStanze = new ArrayList<>();
		nomeStanze.addAll(Arrays.asList(stanza1, stanza2));
	}
	
	
	/**
	 * Metodo che restituisce la mappa delle stanze
	 * @return {@link Map<{@link Stanza}>}
	 */
	public Set<Stanza> getStanze() {
		return collegamento;
	}
	
	
	
	
	//METODI DI MOVIMENTO
	/**
	 * Metodo che preso un personaggio, se il link è aperto setta la nuova posizione del personaggio nell'altrea stanza, 
	 * altriemnti returna false
	 * @param p
	 * @return
	 */
	public boolean passaggio(Personaggio  p) {
		if(aperto) {
			p.setPosizione(collegamento.stream().filter(x -> !(p.getPosizione().equals(x))).findAny().orElse(p.getPosizione()));
			return true;
		}
		
		return false;
	}
	
	/**
	 * Metodo che ritorna se il collegamento � aperto o meno
	 * @return {@link TipoLink}
	 */
	public void apri() {
		System.out.println((aperto ? "È aperto il passaggio!" : "È chiuso il passaggio!"));
	}
	
	
	//METODI DI VERIFICA
	/**
	 * Metodo che verifica se la stanza � effettivamente collegata da questo link
	 * @param stanza
	 * @return boolean
	 */
	public boolean connected(Stanza stanza) {
		return collegamento.contains(stanza);
	}
	
	/**
	 * Metodo per verificare se il link � tra la stanza1 e la stanza2
	 * @param stanza1
	 * @param stanza2
	 * @return boolean
	 */
	public boolean connected(Stanza stanza1, Stanza stanza2) {
		return connected(stanza1) && connected(stanza2);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null || !o.getClass().equals(getClass())) 
			return false;
		
		if(o == this)
			return true;
		
		Link s = (Link) o;
		
		return 	NOME.equals(s.NOME) &&
				collegamento.equals(s.collegamento);
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(NOME, collegamento);
	}
	
	
	@Override
	public void converti() throws EntitaException {
		for(String stanza : nomeStanze)
			collegamento.add((Stanza) AnalizzaFile.convertitore(stanza));
	}
}
