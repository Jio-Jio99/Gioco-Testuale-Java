package it.uniroma1.textadv.entita.interfaccia;

import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public interface Utilizzato {
	public void effetto(Utilizzatore e) throws AzioneException;
}
