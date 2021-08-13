package entita.link;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import entita.Entita;
import entita.oggetto.Oggetto;
import entita.stanza.Stanza;

/**
 * Classe astratta dei link di collegamento delle varie stanze, contente:
 * <pre>
 *  - Nome
 *  - un set delle due stanze collegate da questo link
 * 
 * @author gioele
 */
public abstract class Link extends Entita{
	protected boolean aperto;
	private Set<Stanza> collegamento;
	
	public Link(String nome, Stanza stanza1, Stanza stanza2) {
		super(nome);
		collegamento = new HashSet<>();
		collegamento.addAll(Arrays.asList(stanza1, stanza2));
	}
	
	
	/**
	 * Metodo che restituisce il Set delle stanze
	 * @return {@link Set<{@link Stanza}>}
	 */
	public Set<Stanza> getStanze() {
		return collegamento;
	}
	
	/**
	 * Metodo che verifica se la stanza è effettivamente collegata da questo link
	 * @param stanza
	 * @return boolean
	 */
	public boolean connected(Stanza stanza) {
		return collegamento.contains(stanza);
	}
	
	/**
	 * Metodo per verificare se il link è tra la stanza1 e la stanza2
	 * @param stanza1
	 * @param stanza2
	 * @return boolean
	 */
	public boolean connected(Stanza stanza1, Stanza stanza2) {
		return connected(stanza1) && connected(stanza2);
	}
	
	/**
	 * Metodo che ritorna se il collegamento è aperto o meno
	 * @return {@link TipoLink}
	 */
	public boolean getStato() {
		return aperto;
	}
	
	public abstract void setStato(Oggetto oggetto);
	
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
}
