package utilita.interfaccie.tag;

import utilita.eccezioni.concreto.EntitaException;

public interface Observable {
	void registraObserver(Observer o);
	void cancellaObserver(Observer o);
	void notifica() throws EntitaException;
}

