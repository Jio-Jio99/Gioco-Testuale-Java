package entita.oggetto.concreto;

import entita.oggetto.Oggetto;
import utilita.interfaccie.Inventario;

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
