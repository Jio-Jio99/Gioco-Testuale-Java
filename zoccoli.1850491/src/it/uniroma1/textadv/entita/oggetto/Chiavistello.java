package it.uniroma1.textadv.entita.oggetto;

import it.uniroma1.textadv.entita.interfaccia.Apribile;
import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.utilita.creazione.AnalizzaFile;
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.EntitaException;
import it.uniroma1.textadv.utilita.creazione.interfaccia.Observer;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.ChiaveNonCorrispondenteException;

public abstract class Chiavistello extends Oggetto implements Observer, Inventario{
	protected Apribile porta;
	protected String portaString;
	
	public Chiavistello(String nome) {
		super(nome);
	}
	
	public void setPorta(String nomePorta) {
		portaString = nomePorta.strip();
	}
	
	public Apribile getPorta() {
		return porta;
	}
	
	//METODO PER INTERAZIONE
	public void usa(Apribile e) throws ChiaveNonCorrispondenteException {
		if(e.equals(porta)) {
			e.sblocca();
			return;
		}
		
		throw new ChiaveNonCorrispondenteException(this, e);
	}
	
	@Override
	public void converti() throws EntitaException {
		if(portaString != null)
			porta = (Apribile) AnalizzaFile.convertitore(portaString);
	}
}
