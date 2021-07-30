package entita.link;

/**
 * Classe enumerativa che classifica i tipi di {@link Link} presenti nel Mondo
 * @author gioele
 */
public enum TipoLink {
	PORTA,
	TELETRASPORTO,
	BOTOLA,
	FUORI_CASA;
	
	
	public String getName() {
		return name().charAt(0) + name().substring(1).toLowerCase().replace('_', ' ');
	}
}
