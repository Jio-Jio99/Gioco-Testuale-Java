package entita.link;

import entita.oggetto.Oggetto;
import utilita.creazione.eccezioni.concreto.LinkFileException;

public abstract class MezzoDiTrasporto extends Link {
	
	public MezzoDiTrasporto(String nome, String stanza1, String stanza2) throws LinkFileException {
		super(nome, stanza1, stanza2);
	}
	
	
	@Override
	public void apriCon(Oggetto o) {
		System.out.println("Può già partire... non deve essere 'aperto'!");
	}
}