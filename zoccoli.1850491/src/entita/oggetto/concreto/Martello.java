package entita.oggetto.concreto;

import entita.oggetto.Oggetto;
import utilita.interfaccie.Inventario;

public class Martello extends Oggetto implements Inventario{

	public Martello(String nome) {
		super(nome);
	}

}
