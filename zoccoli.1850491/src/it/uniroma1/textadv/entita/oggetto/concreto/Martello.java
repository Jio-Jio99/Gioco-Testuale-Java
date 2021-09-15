package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.entita.interfaccia.Utilizzato;
import it.uniroma1.textadv.entita.interfaccia.Utilizzatore;
import it.uniroma1.textadv.entita.oggetto.Oggetto;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public class Martello extends Oggetto implements Inventario,Utilizzatore{

	public Martello(String nome) {
		super(nome);
	}

	@Override
	public void usa(Utilizzato o) throws AzioneException {
		o.effetto(this);
	}
}
