package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.interfaccia.Apribile;
import it.uniroma1.textadv.entita.interfaccia.Utilizzato;
import it.uniroma1.textadv.entita.interfaccia.Utilizzatore;
import it.uniroma1.textadv.entita.oggetto.Chiavistello;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.ChiaveNonCorrispondenteException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.UsabileException;

public class Vite extends Chiavistello implements Utilizzato{
	private boolean usabile;
	
	public Vite(String nome) {
		super(nome);
	}
	
	public boolean getStato() {
		return usabile;
	}
	
	@Override
	public String guarda() {
		return "È una " + getNome().toLowerCase();
	}


	@Override
	public void usa(Apribile e) throws ChiaveNonCorrispondenteException {
		if(e.equals(porta) && usabile)
			e.sblocca();
		else if(!usabile)
			System.out.println("Serve un cacciavite per poter usare la vite!");
		else
			throw new ChiaveNonCorrispondenteException(this, e);
	}

	@Override
	public void effetto(Utilizzatore e) throws AzioneException {
		if(e instanceof Cacciavite && !usabile) {
				usabile = true;
				porta.sblocca();
			return;
		}
		
		throw new UsabileException(e, this);
	}
}
