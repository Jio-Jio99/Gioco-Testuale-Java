package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.entita.interfaccia.Utilizzato;
import it.uniroma1.textadv.entita.interfaccia.Utilizzatore;
import it.uniroma1.textadv.entita.oggetto.Oggetto;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

/**
 * Classe Secchio, Utilizzatore 
 * @author gioele
 *
 */
public class Secchio extends Oggetto implements Inventario, Utilizzatore{
	private boolean acqua;
	
	public Secchio(String nome) {
		super(nome);
	}
	
	/**
	 * Metodo che ritorna se il secchio è pieno o meno
	 * @return
	 */
	public boolean getStato() {
		return acqua;
	}

	/**
	 * Metodo che riempe il Secchio
	 */
	public void riempi() {
		acqua = true;
	}

	/**
	 * Metodo che svuota il secchio
	 */
	public void svuota() {
		acqua = false;
	}

	@Override
	public void usa(Utilizzato o) throws AzioneException {
		if(!acqua && o instanceof Camino) {
			System.out.println("Il Secchio è vuoto! Coma fai a spegnere il fuoco senza acqua?");
			return;
		}
		
		o.effetto(this);
	}
}
