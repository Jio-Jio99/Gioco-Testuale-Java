package entita.link.concreto;

import entita.link.Link;
import entita.oggetto.Oggetto;
import entita.stanza.Stanza;

public class Libero extends Link{
	public static final String accesso = "Accesso libero a ";
	private String nomeStanza;
	
	public Libero(String nome, Stanza stanza1, Stanza stanza2){
		super(accesso + nome, stanza1, stanza2);
		this.nomeStanza = nome;
		aperto = true;
	}
	
	public String getNomeStanzaVisibile() {
		return nomeStanza;
	}
	
	@Override
	public String guarda() {
		return "È un passaggio libero...  vedi l'altra parte... interessante! È " + nomeStanza;
	}

	@Override
	public void apriCon(Oggetto o) {
		System.out.println("È già aperto!");
	}
}