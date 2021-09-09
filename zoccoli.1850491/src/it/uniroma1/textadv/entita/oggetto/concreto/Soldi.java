package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.oggetto.Oggetto;
import it.uniroma1.textadv.utilita.interfaccie.Inventario;

public class Soldi extends Oggetto implements Inventario {

	public Soldi(String nome) {
		super(nome);
	}
	
	@Override
	public String guarda() {
		return "Sono dei soldi!! Uaoh ricco!";
	}
}
