package it.uniroma1.textadv.entita.interfaccia;

import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

/**
 * Interfaccia che accomuna tutte le entita che agiscono in modo attivo su altre e ne sbloccano alcune capacità
 * @author gioele
 *
 */
public interface Utilizzatore {
	void usa(Utilizzato o) throws AzioneException;
}
