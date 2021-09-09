package it.uniroma1.textadv.utilita.creazione.interfaccia;

import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.EntitaException;

/**
 * Interfaccia Observable per gestire gli Observer e notificare i cambiamenti
 * @author gioele
 *
 */
public interface Observable {
	/**
	 * Metodo che registra gli obsever dalla lista
	 */
	void registraObserver(Observer o);
	
	/**
	 * Metodo che cancella gli observer dalla lista
	 */
	void cancellaObserver(Observer o);
	
	/**
	 * Metodo che notifica cambiamenti a tutti gli observer
	 */
	void notifica() throws EntitaException;
}

