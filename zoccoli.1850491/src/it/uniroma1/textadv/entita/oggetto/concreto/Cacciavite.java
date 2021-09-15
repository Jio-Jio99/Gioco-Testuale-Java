package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.entita.interfaccia.Utilizzato;
import it.uniroma1.textadv.entita.interfaccia.Utilizzatore;
import it.uniroma1.textadv.entita.oggetto.Oggetto;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.UsabileException;

public class Cacciavite extends Oggetto implements Inventario, Utilizzatore{

	public Cacciavite(String nome) {
		super(nome);
	}

	@Override
	public void usa(Utilizzato o) throws AzioneException {
		if(o instanceof Vite) 
			o.effetto(this);
		else
			throw new UsabileException(this, o);
	}
}
