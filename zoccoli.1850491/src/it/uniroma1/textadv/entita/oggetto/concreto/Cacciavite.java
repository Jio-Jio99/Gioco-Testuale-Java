package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.entita.oggetto.Oggetto;

public class Cacciavite extends Oggetto implements Inventario{

	public Cacciavite(String nome) {
		super(nome);
	}

}
