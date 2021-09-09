package it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.interfaccia.Apribile;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public class ChiusoException extends AzioneException{
	public static final String ERRORE = " è chiuso a chiave!";
	
	public ChiusoException(Apribile nome) {
		super(((Entita)nome).getNome() +  ERRORE);
	}
}
