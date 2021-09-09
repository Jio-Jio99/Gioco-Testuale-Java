package it.uniroma1.textadv.entita.oggetto;

import it.uniroma1.textadv.entita.link.Link;
import it.uniroma1.textadv.utilita.creazione.AnalizzaFile;
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.EntitaException;
import it.uniroma1.textadv.utilita.creazione.interfaccia.Observer;
import it.uniroma1.textadv.utilita.interfaccie.Inventario;

public abstract class Chiavistello extends Oggetto implements Observer, Inventario{
	protected Link porta;
	protected String portaString;
	
	public Chiavistello(String nome) {
		super(nome);
	}
	
	public void setPorta(String nomePorta) {
		portaString = nomePorta.strip();
	}
	
	public Link getPorta() {
		return porta;
	}
	
	
	@Override
	public void converti() throws EntitaException {
		if(portaString != null)
			porta = (Link) AnalizzaFile.convertitore(portaString);
	}
}
