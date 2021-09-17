package it.uniroma1.textadv.entita.oggetto.concreto;

import java.nio.file.Paths;
import java.util.List;

import it.uniroma1.textadv.Gioco;
import it.uniroma1.textadv.entita.StatoGioco;
import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.entita.oggetto.Contenitore;
import it.uniroma1.textadv.entita.personaggio.concreto.Giocatore;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.interfaccieSupporto.FilesMethod;

/**
 * Classe che rappresenta il Tesoro, oggetto finale per la vittoria
 * @author gioele
 *
 */
public class Tesoro extends Contenitore implements Inventario {

	public Tesoro(String nome) {
		super(nome);
		chiusoAChiave = false;
	}
	
	@Override
	public String guarda() {
		return aperto ? "Ha qualcosa dentro... oltre a tanti DIAMANTI...c'è " + (vuoto ? " nulla ":inventario) :"È IL TESOROOOOOO";
	}
	
	@Override
	public void apri() {
		try {
			apriPrivato();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Funzione che all'apertura del tesoro avvia un piccolo easter egg se contiene qualcosa
	 * @throws GiocatoreException
	 */
	private void apriPrivato() throws GiocatoreException {
		List<String> discorso = FilesMethod.lettura(Paths.get("resourse", "easterEgg.txt")).orElseThrow();
		boolean correttaCitazione = false;
		
		System.out.println("\n" + discorso.get(0).replace("Vincent", Giocatore.getInstance().getNome()));
		
		correttaCitazione = discorso.subList(1, 4).contains(Gioco.input());
		
		if(!correttaCitazione)
			System.out.println(discorso.get(4));
		else {
			aperto = true;
			System.out.println(discorso.get(3));
			Giocatore.getInstance().setStato(StatoGioco.IN_GIOCO);
		}
	}
}
