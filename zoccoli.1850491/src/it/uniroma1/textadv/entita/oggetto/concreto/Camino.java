package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.interfaccia.Utilizzato;
import it.uniroma1.textadv.entita.interfaccia.Utilizzatore;
import it.uniroma1.textadv.entita.oggetto.Oggetto;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.UsabileException;

//inizialmente acceso
public class Camino extends Oggetto implements Utilizzato {
	private boolean acceso;
	
	public Camino(String nome) {
		super(nome);
		acceso = true;
	}
	

	public boolean getStato() {
		return acceso;
	}
	
	public void spegni() {
		acceso = false;
	}
	
	public void accendi() {
		acceso = true;
	}


	@Override
	public void effetto(Utilizzatore o) throws AzioneException {
		if(o instanceof Secchio) {
			if(acceso) {
				spegni();
				System.out.println("Nuvola di fumo! Camino spento!");
			}
			else
				System.out.println("Il camino è già spento!");
		}
		else if(acceso)
			throw new UsabileException(o, this);
		else
			throw new AzioneException("STAI BRUCIANDO " + ((Entita)o).getNome() + "!!");
	}
}
