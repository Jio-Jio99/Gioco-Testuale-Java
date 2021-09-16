package it.uniroma1.textadv.utilita.funzionamento.azione.concreto;

import java.util.Set;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.interfaccia.Datore;
import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.entita.link.MezzoDiTrasporto;
import it.uniroma1.textadv.entita.personaggio.concreto.Giocatore;
import it.uniroma1.textadv.entita.stanza.Stanza;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.azione.Azione;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

/**
 * Classe per l'azione del prendere, dato un oggetto prendibile (vedi {@link Inventario}) esso lo prende da chi lo possiede per darlo al Giocatore
 * @author gioele
 *
 */
public class Prendere extends Azione{
	public static final Set<String> COMANDI = Set.of("prendi");
	public static final String DA = "da";
	
	public Prendere() {
		super(COMANDI);
	}
	
	/**
	 * Metodo statico che vede a chi o cosa appartiene l'oggetto richiesto, e lo ritorna
	 * @param stanza
	 * @param nomeOggetto
	 * @return
	 */
	public static Entita daChi(Stanza stanza, String nomeOggetto) {
		Entita daChi = null;
		daChi = stanza.getPersonaggi().values().stream().filter(x -> x.containsOggetto(nomeOggetto)).findAny().orElse(null);
		
		if(daChi == null)
			daChi = stanza.entitaNascosta(nomeOggetto);

		if(daChi == null)
			daChi = stanza;
	
		return daChi;
	}
	
	/**
	 * Metodo che prende un oggetto e il suo possessore, per farlo interagire con il giocatore tramite l'azione dare del {@link Datore}
	 */
	@Override
	public void active(Entita prendere, Entita... daChi) throws AzioneException, GiocatoreException {
		if(prendere instanceof MezzoDiTrasporto) 
			new Movimento().active(prendere, daChi);
		else {
			Inventario in = (Inventario) ((Datore) daChi[0]).dai(prendere.getNome());
			Giocatore.getInstance().prendi(in);
		}
	}
}
