package it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto;

import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public class SpostatoEntitaException extends AzioneException{
	public static String ERRORE = "Quello che cerchi adesso non è più qui! Oppure già lo puoi utilizzare";

	public SpostatoEntitaException() {
		super(ERRORE);
	}
}
