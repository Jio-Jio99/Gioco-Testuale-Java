package it.uniroma1.textadv.entita.personaggio.concreto;


import java.util.HashSet;
import java.util.Set;

import it.uniroma1.textadv.Gioco;
import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.entita.oggetto.concreto.Soldi;
import it.uniroma1.textadv.entita.personaggio.Umano;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public class Venditore extends Umano{
	private Set<String> oggettiRichiesti;
	
	public Venditore(String nome) {
		super(nome);
		oggettiRichiesti = new HashSet<>();
	}
	
	@Override
	public void interazione() {
		super.interazione();
		System.out.println("«Come sta? »");
		
		Gioco.input();
		
		System.out.println("«AHAHAH Farò finta di aver capito... purtoppo il nostro programmatore è un po' pigro e non è che\n"
				+ "ci abbia detto molto su come parlare! Scusami ancora, ma volevo essere gentile! Spero comunque tu stia bene!»");
	}
	
	@Override
	public Inventario dai(String nomeInventario) throws AzioneException {
		if(inventario.containsKey(nomeInventario))
			oggettiRichiesti.add(nomeInventario);
		
		if(inventario.values().stream().anyMatch(x -> x instanceof Soldi))
			return getOggetto(nomeInventario).orElseThrow(() -> new AzioneException("«Purtoppo non ho questo oggetto nel negozio!»"));
		
		throw new AzioneException("«Ehm... prima i sordi grazie! Non posso darti " + nomeInventario + " se prima non paghi!»");
	}
	
	
	@Override
	public void prendi(Inventario o) {
		super.prendi(o);
		
		if(o instanceof Soldi) {
			oggettiRichiesti.stream().map(x ->(Entita) inventario.get(x)).forEach(x -> {
				try {
					daiA(x.getNome(), Giocatore.getInstance());
				} catch (GiocatoreException | AzioneException e) {
					e.printStackTrace();
				}
			});
		}
	}
}
