package it.uniroma1.textadv.entita.interfaccia;

import it.uniroma1.textadv.entita.link.Link;
import it.uniroma1.textadv.entita.oggetto.Contenitore;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.ChiaveNonCorrispondenteException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.ChiusoException;
/**
 * Interfaccia che accomuna tutte le entita che dispongono di un apertura [ex: {@link Contenitore} o {@link Link}]
 * @author gioele
 *
 */
public interface Apribile {
	/**
	 * Metodo per aprire l'entita
	 * @throws ChiusoException = lanciata in caso in cui sia chiusa a chiave
	 */
	public void apri() throws ChiusoException;
	/**
	 * Metodo che apre le entita, usato dai chiavistelli
	 * @throws ChiaveNonCorrispondenteException
	 */
	public void sblocca() throws ChiaveNonCorrispondenteException;
	/**
	 * Metodo per richiudere 
	 */
	public void chiudi();
}
