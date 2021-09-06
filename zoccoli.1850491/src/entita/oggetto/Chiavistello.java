package entita.oggetto;

import entita.link.Link;
import utilita.creazione.AnalizzaFile;
import utilita.creazione.eccezioni.concreto.EntitaException;
import utilita.creazione.interfaccia.Observer;

public abstract class Chiavistello extends Oggetto implements Observer{
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
