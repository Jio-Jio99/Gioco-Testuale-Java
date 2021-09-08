package entita.oggetto.concreto;

import entita.oggetto.Contenitore;
import entita.oggetto.Oggetto;

public class Scrivania extends Contenitore{

	public Scrivania(String nome) {
		super(nome);
		aperto = true;
	}

	@Override
	public void apriCon(Oggetto e) {
		
	}
}
