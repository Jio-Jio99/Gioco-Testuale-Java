package it.uniroma1.textadv.entita.interfaccia;

import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

/**
 * Interfaccia per identificare tutti gli oggetti che subiscono un effetto se messi in contatto con altre entita, quali {@link Utilizzatore}
 * @author gioele
 *
 */
public interface Utilizzato {
	/**
	 * Effetto dell'utilizzo, a seconda di quale struemento esso sia, si ha un effetto o meno
	 * @param e
	 * @throws AzioneException
	 */
	public void effetto(Utilizzatore e) throws AzioneException;
}
