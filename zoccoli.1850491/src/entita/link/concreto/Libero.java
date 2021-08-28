package entita.link.concreto;

import entita.link.Link;
import entita.oggetto.Oggetto;
import entita.stanza.Stanza;
import utilita.eccezioni.concreto.EntitaException;

public class Libero extends Link{
	public static final String NOME = "libero";
	
	public Libero(String stanza1, String stanza2) throws EntitaException {
		super(NOME, stanza1, stanza2);
		converti();
	}
}
