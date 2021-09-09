package it.uniroma1.textadv;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import it.uniroma1.textadv.entita.Mondo;
import it.uniroma1.textadv.entita.personaggio.concreto.Giocatore;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.AnalizzaComando;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;
import it.uniroma1.textadv.utilita.interfaccie.FilesMethod;

public class Gioco {
	public static final Scanner scan = new Scanner(System.in);
	private static List<String> azioni;
	private static int index;
	
	/**
	 * Metodo che mette in azione il gioco tramite uno script
	 * @param m = Mondo
	 * @param scriptDiGioco = script di gioco
	 * @throws Exception
	 */
	public void play(Mondo m, Path scriptDiGioco) throws Exception {
		azioni = FilesMethod.lettura(scriptDiGioco).stream().map(x -> x.split("//")[0].strip()).collect(Collectors.toList());
		play(m, Gioco::inputDaLista);
	}
	
	/**
	 * Metodo che mette in azione il gioco tramite l'input da tastiera, quindi l'utente
	 * @param m = Mondo
	 * @throws Exception
	 */
	public void play(Mondo m) throws Exception {
		play(m, Gioco::input);
	}
	
	/**
	 * Metodo che aziona il gioco
	 * @param m
	 * @param funzioneInput = quale input ricevere (se da tastiera o da script)
	 * @throws GiocatoreException
	 */
	private void play(Mondo m, Supplier<String> funzioneInput) throws GiocatoreException{
		//ciclo di comandi
		System.out.println(m.guarda());
		String s = "";
		while(!Giocatore.getInstance().getInventario().containsKey("tesoro")) {
			s = funzioneInput.get();
			
			try {
				AnalizzaComando.analizzaComando(m, s);
			}
			catch(AzioneException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("\n\t\t\tfine");
		scan.close();
	}
	
	/**
	 * Metodo di supporto per standardizzare in tutto il programma l'input
	 * @return String 
	 */
	public static String input() {
		System.out.print("\n>> ");
		return scan.nextLine();
	}
	
	private static String inputDaLista() {
		String s = azioni.get(index++);
		System.out.print("\n>> " + s);
		return s;
	}
}
