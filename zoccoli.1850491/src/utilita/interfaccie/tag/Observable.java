package utilita.interfaccie.tag;

import utilita.eccezioni.concreto.EntitaException;

/**
 * Interfaccia Observable per gestire gli Observer e notificare i cambiamenti
 * @author gioele
 *
 */
public interface Observable {
	void registraObserver(Observer o);
	void cancellaObserver(Observer o);
	void notifica() throws EntitaException;
}

