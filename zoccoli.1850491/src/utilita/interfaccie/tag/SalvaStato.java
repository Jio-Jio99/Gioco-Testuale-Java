package utilita.interfaccie.tag;

import java.io.Serializable;
import java.nio.file.Path;

/**
 * Interfaccia che rende gli oggetti {@link Serializable} e mette a disposizione dell'oggetto 
 * due metodi per caricare e/o salvare
 * l'oggetto nel file con {@link Path} passato come parametro
 * @author gioele
 */
public interface SalvaStato extends Serializable{
	/**
	 * Metodo che salva l'oggetto nel file
	 * @param fileName {@link Path}
	 */
	void salva(Path fileName);
	
	/**
	 * Metodo che carica dal file binario l'oggetto e restituisce l'oggetto costruito 
	 * @param fileName
	 * @return {@link Object}
	 */
	Object carica(Path fileName);
}
