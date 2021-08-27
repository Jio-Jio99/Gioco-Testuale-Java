package utilita.interfaccie.tag;

import utilita.eccezioni.concreto.EntitaException;

public interface Observer {
	/**
	 * Metodo che prende i nomi in Stringa dell'Entità e restituisce l'instanza dell'oggetto corrispettivo
	 * @throws EntitaException
	 */
	void converti() throws EntitaException;
}
