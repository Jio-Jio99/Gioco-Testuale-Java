package it.uniroma1.textadv.utilita.funzionamento.azione.concreto;

import java.util.Set;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.interfaccia.Apribile;
import it.uniroma1.textadv.entita.oggetto.Chiavistello;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.azione.Azione;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public class Aprire extends Azione{
	public static final Set<String> COMANDI = Set.of("apri");
	public static final String 	APRI = "apri",
								CON = "con";
	public Aprire() {
		super(COMANDI);
	}

	@Override
	public void active(Entita entita1, Entita... entita2) throws AzioneException, GiocatoreException {
		Apribile ap = (Apribile) entita1;
		
		if(entita2.length < 1)
			ap.apri();
		else 
			((Chiavistello) entita2[0]).usa(ap);
	}
}