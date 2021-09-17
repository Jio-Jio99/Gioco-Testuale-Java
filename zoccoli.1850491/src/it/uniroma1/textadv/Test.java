package it.uniroma1.textadv;

import java.nio.file.Paths;
import java.util.stream.Collectors;

import it.uniroma1.textadv.entita.Mondo;
import it.uniroma1.textadv.utilita.creazione.eccezioni.FileNonEsistenteOVuotoException;
import it.uniroma1.textadv.utilita.interfaccieSupporto.FilesMethod;

public class Test{
	public static void main(String[] args) throws Exception{
		String path = "";
		Gioco g = new Gioco();
		Mondo m = null;
		
		System.out.print(getMessage("benvenuto.txt"));
		
		while(!path.equals("exit")) {
			path = Gioco.input();
			try {
				m = Mondo.fromFile(Paths.get(path));
				System.err.println("\n\t\t\tCARICATO CON SUCCESSO!");
				break;
			}
			catch(FileNonEsistenteOVuotoException e) {
				System.out.println(e + " Prova con un'altra directory!");
			}
		}
		
		if(!path.equals("exit")) {
			System.out.println(getMessage("benvenuto2.txt"));
			
			path = Gioco.input();

			if(path.equals("no"))
				g.play(m);
			else
				g.play(m, Paths.get(path));
		}
	}
	
	/**
	 * Legge i file contenuti dentro la cartella resourse, e li combina come una stringa
	 * @param path
	 * @return
	 * @throws FileNonEsistenteOVuotoException
	 */
	private static String getMessage(String path) throws FileNonEsistenteOVuotoException {
		return FilesMethod.lettura(Paths.get("resourse", path)).orElseThrow(FileNonEsistenteOVuotoException::new).stream().collect(Collectors.joining("\n"));
	}
}