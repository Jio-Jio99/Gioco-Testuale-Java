package it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto;

import it.uniroma1.textadv.entita.interfaccia.Apribile;
import it.uniroma1.textadv.entita.oggetto.Oggetto;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public class ChiaveNonCorrispondenteException extends AzioneException{

	public ChiaveNonCorrispondenteException(Oggetto e, Apribile c) {
		super("L'oggetto " + e.getNome() + " non può aprire " + c);
	}
}
