package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.interfaccia.Utilizzato;
import it.uniroma1.textadv.entita.interfaccia.Utilizzatore;
import it.uniroma1.textadv.entita.oggetto.Contenitore;
import it.uniroma1.textadv.entita.personaggio.concreto.Giocatore;
import it.uniroma1.textadv.entita.stanza.Stanza;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.UsabileException;

public class Salvadanaio extends Contenitore implements Utilizzato{

	public Salvadanaio(String nome) {
		super(nome);
	}

	@Override
	public void effetto(Utilizzatore e) throws AzioneException {
		if(e == null) 
			System.out.println("Non puoi aprire il Salvadanaio solamente con le mani! È bello duro! Trova qualcosa!");
		else if(e instanceof Martello && !aperto) {
			sblocca();
			
			try {
				Stanza s = Giocatore.getInstance().getPosizione();
				s.aggiungiElemento(inventario);
				s.removeOggetto(getNome());

			} catch (GiocatoreException e1) {
				e1.printStackTrace();
			}
			
			System.out.println("Rotto!");
		}
		else if(aperto)
			System.out.println("È già aperto!");
		else
			throw new UsabileException(e, this);
	}
}
