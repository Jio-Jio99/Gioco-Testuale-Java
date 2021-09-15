package it.uniroma1.textadv.utilita.funzionamento.azione.concreto;

import java.util.Set;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.interfaccia.Datore;
import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.entita.link.MezzoDiTrasporto;
import it.uniroma1.textadv.entita.personaggio.concreto.Giocatore;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.azione.Azione;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public class Prendere extends Azione{
	public static final Set<String> COMANDI = Set.of("prendi");
	public static final String DA = "da";
	
	public Prendere() {
		super(COMANDI);
	}
	
	@Override
	public void active(Entita prendere, Entita... daChi) throws AzioneException, GiocatoreException {
		if(prendere instanceof MezzoDiTrasporto) 
			new Movimento().active(prendere, daChi);

		Inventario in = (Inventario) ((Datore) daChi[0]).dai(prendere.getNome());
		Giocatore.getInstance().prendi(in);
	}
}
