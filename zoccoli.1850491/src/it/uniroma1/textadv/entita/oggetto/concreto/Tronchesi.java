package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.oggetto.Oggetto;
import it.uniroma1.textadv.utilita.interfaccie.Inventario;

public class Tronchesi extends Oggetto implements Inventario{

	public Tronchesi(String nome) {
		super(nome);
	}
	
	
	@Override
	public String guarda() {
		return "È una" + getNome().toLowerCase();
	}
}
