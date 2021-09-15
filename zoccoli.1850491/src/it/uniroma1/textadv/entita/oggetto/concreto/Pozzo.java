package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.interfaccia.Utilizzato;
import it.uniroma1.textadv.entita.interfaccia.Utilizzatore;
import it.uniroma1.textadv.entita.oggetto.Oggetto;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.UsabileException;

public class Pozzo extends Oggetto implements Utilizzato{

	public Pozzo(String nome) {
		super(nome);
	}

	@Override
	public void effetto(Utilizzatore e) throws AzioneException {
		if(e instanceof Secchio) {
			((Secchio) e).riempi();
			System.out.println("Plufff.... rialzi e ... secchio riempito!");
		}
		else
			throw new UsabileException(e, this);
	}
	
}
