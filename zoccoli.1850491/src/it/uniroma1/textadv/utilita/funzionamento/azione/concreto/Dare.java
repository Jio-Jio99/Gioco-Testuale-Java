package it.uniroma1.textadv.utilita.funzionamento.azione.concreto;

import java.util.Set;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.entita.personaggio.Personaggio;
import it.uniroma1.textadv.entita.personaggio.concreto.Giocatore;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.azione.Azione;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public class Dare extends Azione{
	public static final Set<String> COMANDI = Set.of("dai");
	public static final String A = "a";
	public Dare() {
		super(COMANDI);
	}

	@Override
	public void active(Entita entita1, Entita... entita2) throws AzioneException, GiocatoreException {
		Giocatore.getInstance().dai((Inventario) entita1, (Personaggio) entita2[0]);
	}
}
