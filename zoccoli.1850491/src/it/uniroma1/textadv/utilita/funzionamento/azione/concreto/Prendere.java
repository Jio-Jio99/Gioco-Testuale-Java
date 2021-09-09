package it.uniroma1.textadv.utilita.funzionamento.azione.concreto;

import java.util.List;
import java.util.Set;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.entita.link.MezzoDiTrasporto;
import it.uniroma1.textadv.entita.personaggio.concreto.Giocatore;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.azione.Azione;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public class Prendere extends Azione{
	public static final Set<String> COMANDI = Set.of("prendi");
	
	public Prendere() {
		super(COMANDI);
	}
	
	@Override
	public void active(List<Entita> entita) throws AzioneException, GiocatoreException {
		if(entita.get(0) instanceof MezzoDiTrasporto) {
//			if(entita.length == 1)
//				Giocatore.getInstance().prendi((MezzoDiTrasporto) entita[0]);
//			else
//				throw new IncoerenzaEntitaAzioneException();
			new Movimento().active(entita);
		}

		Giocatore.getInstance().prendi((Inventario) entita.get(0));
	}
}
