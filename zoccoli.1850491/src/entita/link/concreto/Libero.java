package entita.link.concreto;

import entita.link.Link;
import entita.stanza.Stanza;
import utilita.creazione.eccezioni.concreto.LinkFileException;

public class Libero extends Link{
	public static final String NOME = "libero";

	public Libero(Stanza stanza1, Stanza stanza2){
		super(NOME, stanza1, stanza2);
	}
}
