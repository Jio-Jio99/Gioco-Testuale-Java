package entita.link.concreto;

import entita.link.Link;
import entita.oggetto.Oggetto;
import entita.stanza.Stanza;
import utilita.azione.interfaccia.Apribile;

public class Libero extends Link implements Apribile{
	public static final String accesso = "Accesso libero a ";
	
	public Libero(String nome, Stanza stanza1, Stanza stanza2){
		super(accesso + nome, stanza1, stanza2);
		aperto = true;
	}

	@Override
	public void apriCon(Oggetto e) {
		
	}
	
	@Override
	public String guarda() {
		return "È un passaggio libero...  vedi l'altra parte... interessante!";
	}
}
