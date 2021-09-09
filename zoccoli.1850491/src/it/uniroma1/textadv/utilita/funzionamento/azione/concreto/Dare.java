package it.uniroma1.textadv.utilita.funzionamento.azione.concreto;

import java.util.Set;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.entita.personaggio.Personaggio;
import it.uniroma1.textadv.entita.personaggio.concreto.Giocatore;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.azione.Azione;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.OggettoNonInInventarioException;

public class Dare extends Azione{
	public static final Set<String> COMANDI = Set.of("dai");
	
	public Dare() {
		super(COMANDI);
	}
	
	@Override
	public void active(Entita... entita) throws OggettoNonInInventarioException, GiocatoreException {
		Giocatore.getInstance().dai((Inventario) entita[1], (Personaggio) entita[0]);
	}
}
