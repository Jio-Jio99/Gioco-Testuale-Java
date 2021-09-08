package entita.oggetto.concreto;

import entita.oggetto.Oggetto;

//inizialmente acceso
public class Camino extends Oggetto {
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
}
