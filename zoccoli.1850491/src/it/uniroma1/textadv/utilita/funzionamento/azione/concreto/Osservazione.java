package it.uniroma1.textadv.utilita.funzionamento.azione.concreto;

import java.util.Set;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.interfaccia.Description;
import it.uniroma1.textadv.entita.personaggio.concreto.Giocatore;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.azione.Azione;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public class Osservazione extends Azione{
	public static final Set<String> COMANDI = Set.of("osserva", "guarda", "inventario");
	
	public Osservazione() {
		super(COMANDI);
	}
	
	@Override
	public void active(Entita entita1, Entita... entita2) throws GiocatoreException, AzioneException {
		if(comandoRicevuto.equals("inventario")) { 
			if(entita2 != null)
				throw new AzioneException("Non puo eseguire il comando inventario con altre entita!");
			
			Giocatore.getInstance().inventario();
		}
		else if(entita1 != null) {
			Description d = (Description) entita1;
			Giocatore.getInstance().guarda(d);
		}
	}
}
