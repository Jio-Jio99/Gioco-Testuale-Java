package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.entita.oggetto.Oggetto;

public class Martello extends Oggetto implements Inventario{

	public Martello(String nome) {
		super(nome);
	}

}
