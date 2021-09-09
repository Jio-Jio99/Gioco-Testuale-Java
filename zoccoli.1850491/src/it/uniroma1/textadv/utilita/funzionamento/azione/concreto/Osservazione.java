package it.uniroma1.textadv.utilita.funzionamento.azione.concreto;

import java.util.List;
import java.util.Set;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.interfaccia.Description;
import it.uniroma1.textadv.entita.personaggio.concreto.Giocatore;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.azione.Azione;

public class Osservazione extends Azione{
	public static final Set<String> COMANDI = Set.of("osserva", "guarda", "inventario");
	
	public Osservazione() {
		super(COMANDI);
	}
	
	@Override
	public void active(List<Entita> entita) throws GiocatoreException {
		if(comandoRicevuto.equals("inventario")) {
			Giocatore.getInstance().inventario();

			if(!entita.isEmpty())
				System.out.println("Il comando inventario visiona solo il tuo zaioni... non altre cose, quindi ho fatto finta che non mi hai chiesto nulla di " + entita+ "! ;-)");
			
			return;
		}
		
		if(entita.size() == 1) {
			Description d = (Description) entita.get(0);
			Giocatore.getInstance().guarda(d);
			return;
		}
		
		if(entita.isEmpty())
			Giocatore.getInstance().guarda();
		else
			System.out.println("Non puoi guardare più cose assieme... senza offesa per gli strabici");
	}
}
