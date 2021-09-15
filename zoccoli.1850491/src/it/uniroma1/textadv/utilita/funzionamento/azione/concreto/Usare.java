package it.uniroma1.textadv.utilita.funzionamento.azione.concreto;

import java.util.Set;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.interfaccia.Utilizzato;
import it.uniroma1.textadv.entita.interfaccia.Utilizzatore;
import it.uniroma1.textadv.entita.oggetto.Chiavistello;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.azione.Azione;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public class Usare extends Azione{
	public static final Set<String> COMANDI = Set.of("usa", "rompi");
	public static final String 	USA = "usa",
								SU = "su";
	public Usare() {
		super(COMANDI);
	}

	@Override
	public void active(Entita entita1, Entita... entita2) throws AzioneException, GiocatoreException {
		if(entita1 instanceof Chiavistello && entita2 != null && entita2.length > 0) {
			new Aprire().active(entita2[0], entita1);
			return;
		}
		
		if(entita2.length == 0 || entita2 == null)
			throw new AzioneException("Devi usare qualcosa su qualcos'altro per fare l'azione!");
		
		Utilizzatore u = (Utilizzatore) entita1;
		u.usa((Utilizzato) entita2[0]);
	}
}
