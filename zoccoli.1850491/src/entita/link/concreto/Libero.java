package entita.link.concreto;

import java.util.Arrays;

import entita.link.Link;
import entita.stanza.Stanza;
import utilita.eccezioni.concreto.EntitaException;

public class Libero extends Link{
	public static final String NOME = "libero";

	public Libero(Stanza stanza1, Stanza stanza2) throws EntitaException {
		super(NOME, null, null);
		collegamento.addAll(Arrays.asList(stanza1, stanza2));
	}
}
