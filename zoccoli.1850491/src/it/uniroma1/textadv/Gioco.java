package it.uniroma1.textadv;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entita.Mondo;
import utilita.creazione.interfaccia.FilesMethod;
import utilita.enumerazioni.TipoGioco;

public class Gioco {
	
	public void play(Mondo m) throws Exception {
		//inizio gioco
		TipoGioco t = TipoGioco.IN_GIOCO;
		Scanner scan = new Scanner(System.in);
		
		//ciclo di comandi
		while(t.equals(TipoGioco.IN_GIOCO)) {
			
			
			
			
			
		}
	}
	
	public void play(Mondo m, Path scriptDiGioco) throws Exception {
		List<String> azioni = FilesMethod.lettura(scriptDiGioco);
		azioni = azioni.stream().map(x -> x.split("//")[0].strip()).collect(Collectors.toList());
	}
}
