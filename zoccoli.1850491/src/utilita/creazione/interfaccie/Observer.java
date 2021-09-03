package utilita.creazione.interfaccie;

import utilita.creazione.eccezioni.concreto.EntitaException;

/**
 * Interfaccia Observer per convertire le Stringhe in Entita
 * @author gioele
 */
public interface Observer {
	/**
	 * Metodo che prende i nomi in Stringa dell'Entità e restituisce l'instanza dell'oggetto corrispettivo
	 * @throws EntitaException
	 */
	void converti() throws EntitaException;
}
