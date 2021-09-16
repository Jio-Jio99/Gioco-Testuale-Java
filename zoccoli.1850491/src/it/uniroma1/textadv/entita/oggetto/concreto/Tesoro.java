package it.uniroma1.textadv.entita.oggetto.concreto;

import java.nio.file.Paths;
import java.util.List;

import it.uniroma1.textadv.Gioco;
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
		return "È IL TESOROOOOOO";
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
	 * Funzione che all'apertura del tesoro avvia un piccolo easter egg
	 * @throws GiocatoreException
	 */
	private void apriPrivato() throws GiocatoreException {
		List<String> discorso = FilesMethod.lettura(Paths.get("resourse", "easterEgg.txt")).orElseThrow();
		String frase = discorso.get(3);
		
		System.out.println("\n" + discorso.get(0).replace("Vincent", Giocatore.getInstance().getNome()));
		
		if(!discorso.subList(1, 4).contains(Gioco.input())) 
			frase = discorso.get(4);
		
		System.out.println(frase);
	}
}
