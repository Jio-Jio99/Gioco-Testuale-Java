package it.uniroma1.textadv.utilita.funzionamento.azione.concreto;

import java.util.Set;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.azione.Azione;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public class Aprire extends Azione{
	public static final Set<String> COMANDI = Set.of("apri");
	
	public Aprire() {
		super(COMANDI);
	}

	@Override
	public void active(Entita... entita) throws AzioneException, GiocatoreException {
		// TODO Auto-generated method stub
	}
}