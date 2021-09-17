package it.uniroma1.textadv.utilita.funzionamento.azione.concreto;

import java.util.Set;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.interfaccia.Datore;
import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.entita.personaggio.Personaggio;
import it.uniroma1.textadv.entita.personaggio.concreto.Giocatore;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.azione.Azione;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

/**
 * Classe per l'azione Dare, per tutte le entita {@link Datore}
 * @author gioele
 *
 */
public class Dare extends Azione{
	public static final Set<String> COMANDI = Set.of("dai");
	public static final String A = "a";
	public Dare() {
		super(COMANDI, x -> (x instanceof Inventario || x instanceof Personaggio));
	}

	/**
	 * Metodo che riceve un oggetto da dare e a chi
	 */
	@Override
	public void active(Entita oggetto, Entita... aChi) throws AzioneException, GiocatoreException {
		Giocatore.getInstance().dai((Inventario) oggetto, (Personaggio) aChi[0]);
	}
}
