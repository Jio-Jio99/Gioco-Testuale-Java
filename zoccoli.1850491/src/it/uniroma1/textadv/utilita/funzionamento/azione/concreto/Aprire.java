package it.uniroma1.textadv.utilita.funzionamento.azione.concreto;

import java.util.Set;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.interfaccia.Apribile;
import it.uniroma1.textadv.entita.oggetto.Chiavistello;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.azione.Azione;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

/**
 * Classe per l'azione dell'aprire, per tutte le entita che sono {@link Apribile}
 * @author gioele
 *
 */
public class Aprire extends Azione{
	public static final Set<String> COMANDI = Set.of("apri");
	public static final String 	APRI = "apri",
								CON = "con";
	public Aprire() {
		super(COMANDI, x -> (x instanceof Apribile || x instanceof Chiavistello));
	}

	/**
	 * Metodo che apre gli Apribili (EX Link e Contenitori), ricevento un entita da aprire, e nel caso sia chiuso a chiave il suo corrispondente chiavistello
	 */
	@Override
	public void active(Entita daAprire, Entita... chiavistello) throws AzioneException, GiocatoreException {
		Apribile ap = (Apribile) daAprire;
		
		if(chiavistello.length < 1)
			ap.apri();
		else 
			((Chiavistello) chiavistello[0]).usa(ap);
	}
}