package it.uniroma1.textadv.utilita.funzionamento.azione.concreto;

import java.util.List;
import java.util.Set;


import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.interfaccia.Apribile;
import it.uniroma1.textadv.entita.oggetto.Chiavistello;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.azione.Azione;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public class Aprire extends Azione{
	public static final Set<String> COMANDI = Set.of("apri");
	
	public Aprire() {
		super(COMANDI);
	}

	@Override
	public void active(List<Entita> entita) throws AzioneException, GiocatoreException {
		if(entita.size() == 1) 
			((Apribile) entita).apri();
		
		else {
		
		}
	}
	
}