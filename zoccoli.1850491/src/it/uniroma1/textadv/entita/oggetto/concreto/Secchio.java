package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.entita.interfaccia.Utilizzato;
import it.uniroma1.textadv.entita.interfaccia.Utilizzatore;
import it.uniroma1.textadv.entita.oggetto.Oggetto;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

//inizialmente vuoto
public class Secchio extends Oggetto implements Inventario, Utilizzatore{
	private boolean acqua;
	
	public Secchio(String nome) {
		super(nome);
	}
	
	
	public boolean getStato() {
		return acqua;
	}

	public void riempi() {
		acqua = true;
	}

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
