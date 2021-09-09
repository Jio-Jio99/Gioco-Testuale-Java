package it.uniroma1.textadv.utilita.funzionamento.azione.concreto;

import java.util.Arrays;
import java.util.Set;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.personaggio.concreto.Giocatore;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.azione.Azione;
import it.uniroma1.textadv.utilita.funzionamento.interfaccia.Description;

public class Osservazione extends Azione{
	public static final Set<String> COMANDI = Set.of("osserva", "guarda", "inventario");
	
	public Osservazione() {
		super(COMANDI);
	}
	
	@Override
	public void active(Entita... entita) throws GiocatoreException {
		if(comandoRicevuto.equals("inventario")) {
			Giocatore.getInstance().inventario();

			if(entita.length != 0)
				System.out.println("Il comando inventario visiona solo il tuo zaioni... non altre cose, quindi ho fatto finta che non mi hai chiesto nulla di " + Arrays.toString(entita)+ "! ;-)");
			
			return;
		}
		
		if(entita.length == 1) {
			Description d = (Description) entita[0];
			Giocatore.getInstance().guarda(d);
			return;
		}
		
		if(entita.length == 0 || entita == null)
			Giocatore.getInstance().guarda();
		else
			System.out.println("Non puoi guardare più cose assieme... senza offesa per gli strabici");
	}
}
