package entita.oggetto.concreto;

import entita.oggetto.Oggetto;
import utilita.interfaccie.Inventario;

public class Tronchesi extends Oggetto implements Inventario{

	public Tronchesi(String nome) {
		super(nome);
	}
	
	
	@Override
	public String guarda() {
		return "È una" + getNome().toLowerCase();
	}
}
