package it.uniroma1.textadv;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import it.uniroma1.textadv.entita.Mondo;
import it.uniroma1.textadv.entita.StatoGioco;
import it.uniroma1.textadv.entita.personaggio.concreto.Giocatore;
import it.uniroma1.textadv.utilita.creazione.eccezioni.FileNonEsistenteOVuotoException;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.AnalizzaComando;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.ExitException;
import it.uniroma1.textadv.utilita.interfaccieSupporto.FilesMethod;

public class Gioco {
	public static final Scanner scan = new Scanner(System.in);
	private static List<String> azioni;
	private static int index;
	private AnalizzaComando analizzatoreComandi;
	private Mondo mondo;
	
	/**
	 * Metodo che mette in azione il gioco tramite uno script
	 * @param m = Mondo
	 * @param scriptDiGioco = script di gioco
	 * @throws Exception
	 */
	public void play(Mondo m, Path scriptDiGioco) throws Exception {
		azioni = FilesMethod.lettura(scriptDiGioco).orElseThrow(FileNonEsistenteOVuotoException::new).stream().map(x -> x.split("//")[0].strip()).collect(Collectors.toList());
		setMondo(m);
		play(Gioco::inputDaScript);
	}
	
	/**
	 * Metodo che mette in azione il gioco tramite l'input da tastiera, quindi l'utente
	 * @param m = Mondo
	 * @throws Exception
	 */
	public void play(Mondo m) throws Exception {
		setMondo(m);
		play(Gioco::input);
	}
	
	/**
	 * Metodo che aziona il gioco
	 * @param m
	 * @param funzioneInput = quale input ricevere (se da tastiera o da script)
	 * @throws GiocatoreException
	 */
	private void play(Supplier<String> funzioneInput) throws GiocatoreException{
		System.out.println(mondo.guarda());
		StatoGioco stato = StatoGioco.IN_GIOCO;
		
		String s = "";
		//ciclo di comandi
		while(stato == StatoGioco.IN_GIOCO) {
			s = funzioneInput.get();
			
			try {
				analizzatoreComandi.analizzaComando(s);
			}
			catch(ExitException e) {
				break;
			}
			catch(AzioneException e) {
				System.out.println(e.getMessage());
			}
			
			
			stato = Giocatore.getInstance().getStato();
		}
		
		if(stato == StatoGioco.VINTO)
			easterEgg();
		
		scan.close();
		
		System.out.println("\n\t\t\t\tFINE");
	}
	
	/**
	 * Metodo di supporto per standardizzare in tutto il programma l'input
	 * @return String 
	 */
	public static String input() {
		System.out.print("\n>> ");
		return scan.nextLine();
	}
	
	private static String inputDaScript() {
		String s = "";
		
		if(index < azioni.size())
			s = azioni.get(index++);
		else 
			s = "exit";
		
		System.out.println("\n>> " + s);
		return s;
	}
	
	private void setMondo(Mondo m) {
		mondo = m;
		analizzatoreComandi = new AnalizzaComando(m);
	}
	
	
	private void easterEgg(){
		System.out.println("\nHAI VINTO!!! Ma... non vorresti aprire il tesoro?");
		
		if(Set.of("si", "sì").contains(input().toLowerCase())) {
			System.out.println("Allora aprilo!!");
			String input = "";
			
			while(true) {
				input = input().strip().toLowerCase();
				
				if(!"apri tesoro".equals(input))
					System.out.println("Ehm... solo l'azione per aprire il tesoro puoi fare dai!! Non ti perdere! Sono quasi più curioso di te");
				
				else {
					try {
						analizzatoreComandi.analizzaComando(input);
					} catch (GiocatoreException | AzioneException e) {
						e.printStackTrace();
					}
					
					break;
				}
			}
		}
		else 
			System.out.println("Lo prendo come un no! Peccato! Va beh ottima partita comunque!!! È stato un piacere giocare con te!");
	}
	
}
