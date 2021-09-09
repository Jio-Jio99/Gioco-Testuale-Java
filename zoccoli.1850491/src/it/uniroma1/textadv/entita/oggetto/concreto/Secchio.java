package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.oggetto.Oggetto;
import it.uniroma1.textadv.utilita.interfaccie.Inventario;

//inizialmente vuoto
public class Secchio extends Oggetto implements Inventario{
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
}
