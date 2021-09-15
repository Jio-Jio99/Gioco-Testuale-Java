package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.interfaccia.Utilizzato;
import it.uniroma1.textadv.entita.interfaccia.Utilizzatore;
import it.uniroma1.textadv.entita.oggetto.Contenitore;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.UsabileException;

public class Salvadanaio extends Contenitore implements Utilizzato{

	public Salvadanaio(String nome) {
		super(nome);
	}

	@Override
	public void effetto(Utilizzatore e) throws AzioneException {
		if(e instanceof Martello && !aperto) {
			sblocca();
			System.out.println("Rotto!");
		}
		else if(aperto)
			System.out.println("È già aperto!");
		else
			throw new UsabileException(e, this);
	}
}
