package it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto;

import it.uniroma1.textadv.entita.interfaccia.Apribile;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public class ChiusoException extends AzioneException{
	public static final String ERRORE = " è chiuso!";
	public static final String ERRORE_CHIAVE = " è chiuso a chiave!";

	public ChiusoException(Apribile nome, boolean chiave) {
		super(nome + (chiave ? ERRORE_CHIAVE : ERRORE));
	}
}
