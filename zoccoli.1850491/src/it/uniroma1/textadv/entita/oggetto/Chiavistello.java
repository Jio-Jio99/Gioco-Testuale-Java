package it.uniroma1.textadv.entita.oggetto;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.interfaccia.Apribile;
import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.entita.link.concreto.Porta;
import it.uniroma1.textadv.utilita.creazione.AnalizzaFile;
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.EntitaException;
import it.uniroma1.textadv.utilita.creazione.interfaccia.Observer;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.ChiaveNonCorrispondenteException;

/**
 * Classe astratta che rappresenta tutti gli oggetti che fungono da Chiavi per aprire un {@link Apribile}
 * @author gioele
 *
 */
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
	/**
	 * Metodo che usa la chiave sulla porta per sbloccarla, se corrispondente a quella contenuta
	 * @param e
	 * @throws ChiaveNonCorrispondenteException
	 */
	public void usa(Apribile e) throws ChiaveNonCorrispondenteException {
		if(e.equals(porta)) {
			e.sblocca();
			System.out.println("Aperto!");;
			return;
		}
		
		throw new ChiaveNonCorrispondenteException(this, e);
	}
	
	@Override
	public void converti() throws EntitaException {
		Entita e = null;
		
		if(portaString != null) {
			e = AnalizzaFile.convertitore(portaString);
			
			if(e instanceof Porta)
				((Porta)e).chiudiAchiave();
			
			porta = (Apribile) e;
		}
	}
}
