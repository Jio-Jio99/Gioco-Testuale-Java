package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.entita.oggetto.Oggetto;

public class Tronchesi extends Oggetto implements Inventario{

	public Tronchesi(String nome) {
		super(nome);
	}
	
	
	@Override
	public String guarda() {
		return "È una" + getNome().toLowerCase();
	}
}
