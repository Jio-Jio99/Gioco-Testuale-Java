package it.uniroma1.textadv;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entita.Mondo;
import utilita.enumerazioni.TipoGioco;
import utilita.interfaccie.FilesMethod;

public class Gioco {
	public static final Scanner scan = new Scanner(System.in);
	
	public void play(Mondo m) throws Exception {
		//inizio gioco
		TipoGioco t = TipoGioco.IN_GIOCO;
		
		//ciclo di comandi
		while(t.equals(TipoGioco.IN_GIOCO)) {
			String s = input();
			
			
			
			
		}
		
		scan.close();
	}
	
	public void play(Mondo m, Path scriptDiGioco) throws Exception {
		List<String> azioni = FilesMethod.lettura(scriptDiGioco).stream().map(x -> x.split("//")[0].strip()).collect(Collectors.toList());
	}
	
	/**
	 * Metodo di supporto per standardizzare in tutto il programma l'input
	 * @return String 
	 */
	public static String input() {
		System.out.print("\n>> ");
		return scan.nextLine();
	}
}
