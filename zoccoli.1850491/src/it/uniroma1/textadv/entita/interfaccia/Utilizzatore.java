package it.uniroma1.textadv.entita.interfaccia;

import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public interface Utilizzatore {
	void usa(Utilizzato o) throws AzioneException;
}
