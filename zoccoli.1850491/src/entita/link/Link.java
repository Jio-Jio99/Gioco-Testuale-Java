package entita.link;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import entita.stanza.Stanza;

/**
 * Classe astratta dei link di collegamento delle varie stanze, contente:
 * <pre>
 *  - Nome
 *  - {@link TipoLink}
 *  - un set delle due stanze collegate da questo link
 * 
 * @author gioele
 */
public abstract class Link {
	private final String NOME;
	private final TipoLink TIPO;
	private Set<Stanza> collegamento;
	
	
	public Link(String nome, TipoLink tipo, Stanza stanza1, Stanza stanza2) {
		this.NOME = nome;
		this.TIPO = tipo;
		collegamento = new HashSet<>();
		collegamento.addAll(Arrays.asList(stanza1, stanza2));
	}
	
	
	/**
	 * Metodo che restituisce il Set delle stanze
	 * @return {@link Set<{@linkStanza}>}
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
	 * Metodo che restituisce il {@link TipoLink}
	 * @return {@link TipoLink}
	 */
	public TipoLink getTipo() {
		return TIPO;
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
				TIPO.equals(s.TIPO) &&
				collegamento.equals(s.collegamento);
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(NOME, TIPO, collegamento);
	}
}
