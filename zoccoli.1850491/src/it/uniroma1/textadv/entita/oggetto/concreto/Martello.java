package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.oggetto.Oggetto;
import it.uniroma1.textadv.utilita.interfaccie.Inventario;

public class Martello extends Oggetto implements Inventario{

	public Martello(String nome) {
		super(nome);
	}

}
