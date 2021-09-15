package it.uniroma1.textadv.entita.interfaccia;

import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.ChiaveNonCorrispondenteException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.ChiusoException;

public interface Apribile {
	public void apri() throws ChiusoException;
	public void sblocca() throws ChiaveNonCorrispondenteException;
	public void chiudi();
}
