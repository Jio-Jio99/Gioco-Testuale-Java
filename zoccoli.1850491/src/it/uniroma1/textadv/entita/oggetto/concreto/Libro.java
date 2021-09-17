package it.uniroma1.textadv.entita.oggetto.concreto;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import it.uniroma1.textadv.entita.StatoGioco;
import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.entita.interfaccia.Utilizzato;
import it.uniroma1.textadv.entita.interfaccia.Utilizzatore;
import it.uniroma1.textadv.entita.oggetto.Oggetto;
import it.uniroma1.textadv.entita.personaggio.concreto.Giocatore;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;
import it.uniroma1.textadv.utilita.interfaccieSupporto.FilesMethod;

public class Libro extends Oggetto implements Utilizzato, Inventario{
	private String lingua;
	
	public Libro(String nome) {
		super(nome);
		lingua = Arrays.asList(nome.split(" ")).stream().filter(x -> !x.equals("libro")).findAny().orElse("");
	}
	
	
	@Override
	public String guarda() {
		return "È un vecchio libro ingiallito... con scritte in " + NOME.replace("libro", "");
	}


	@Override
	public void effetto(Utilizzatore e) throws AzioneException {
		if(!lingua.isBlank()) {
			lingua += ".txt";
			List<String> lettura = FilesMethod.lettura(Paths.get("resourse", lingua)).orElseThrow();
			
			System.out.println("\t\t\t" + lettura.stream().collect(Collectors.joining("\n\t\t")));
		}
		else 
			System.out.println("Libro vuoto!");
		
		System.out.println("\n\tE con questo ti faccio i miei complimenti! HAI VINTO!");
		try {
			Giocatore.getInstance().setStato(StatoGioco.VINTO);
		} catch (GiocatoreException e1) {
			e1.printStackTrace();
		}
	}
}
