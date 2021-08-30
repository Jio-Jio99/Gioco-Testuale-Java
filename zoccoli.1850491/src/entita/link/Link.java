package entita.link;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import entita.Entita;
import entita.oggetto.Oggetto;
import entita.stanza.Stanza;
import utilita.eccezioni.concreto.EntitaException;
import utilita.interfaccie.AnalizzaFile;
import utilita.interfaccie.tag.Observable;
import utilita.interfaccie.tag.Observer;

/**
 * Classe astratta dei link di collegamento delle varie stanze, contente:
 * <pre>
 *  - Nome
 *  - un set delle due stanze collegate da questo link
 * 
 * @author gioele
 */
public abstract class Link extends Entita implements Observer{
	protected boolean aperto;
	protected List<Stanza> collegamento;
	private List<String> nomeStanze;
	
	private Link(String nome) {
		super(nome);
		collegamento = new ArrayList<>();
	}
	
	/**
	 * Costruttore del Link con già presenti le stanze
	 * @param nome
	 * @param stanza1
	 * @param stanza2
	 */
	public Link(String nome, Stanza stanza1, Stanza stanza2) {
		this(nome);
		collegamento.addAll(Arrays.asList(stanza1, stanza2));
	}
	
	/**
	 * Costruttore che riceve due stringhe, non due stanze, e di conseguenza ha bisogno dell'Observable
	 * @param nome
	 * @param stanza1
	 * @param stanza2
	 */
	public Link(String nome, String stanza1, String stanza2) {
		this(nome);
		nomeStanze = new ArrayList<>();
		nomeStanze.addAll(Arrays.asList(stanza1, stanza2));
	}
	
	
	/**
	 * Metodo che restituisce il Set delle stanze
	 * @return {@link Set<{@link Stanza}>}
	 */
	public List<Stanza> getStanze() {
		return collegamento;
	}
	
	/**
	 * Metodo che verifica se la stanza ï¿½ effettivamente collegata da questo link
	 * @param stanza
	 * @return boolean
	 */
	public boolean connected(Stanza stanza) {
		return collegamento.contains(stanza);
	}
	
	/**
	 * Metodo per verificare se il link ï¿½ tra la stanza1 e la stanza2
	 * @param stanza1
	 * @param stanza2
	 * @return boolean
	 */
	public boolean connected(Stanza stanza1, Stanza stanza2) {
		return connected(stanza1) && connected(stanza2);
	}
	
	/**
	 * Metodo che ritorna se il collegamento ï¿½ aperto o meno
	 * @return {@link TipoLink}
	 */
	public boolean getStato() {
		return aperto;
	}
	
	/**
	 * Metodo che ritorna il nome del link
	 * @return String
	 */
	public String getNome() {
		return NOME;
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
