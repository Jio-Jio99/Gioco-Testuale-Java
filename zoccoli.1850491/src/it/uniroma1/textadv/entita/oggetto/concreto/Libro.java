package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.interfaccia.Utilizzato;
import it.uniroma1.textadv.entita.interfaccia.Utilizzatore;
import it.uniroma1.textadv.entita.oggetto.Oggetto;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public class Libro extends Oggetto implements Utilizzato{
	
	public Libro(String nome) {
		super(nome);
	}
	
	
	@Override
	public String guarda() {
		return "È un vecchio libro ingiallito... con scritte in " + NOME.replace("libro", "");
	}


	@Override
	public void effetto(Utilizzatore e) throws AzioneException {
		
	}
}
