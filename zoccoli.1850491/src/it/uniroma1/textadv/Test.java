package it.uniroma1.textadv;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import it.uniroma1.textadv.utilita.creazione.eccezioni.FileNonEsistenteOVuotoException;
import it.uniroma1.textadv.utilita.interfaccieSupporto.FilesMethod;

public class Test{
	public static final Path PATH_MONDO_DEFAULT = Paths.get("resourse","minizak2_0.game");
	public static final Path PATH_SCRIPT_DEFAULT = Paths.get("resourse","minizak.ff");

	public static void main(String[] args) throws Exception{
		String pathString = "";
		Gioco g = new Gioco();
		Mondo m = null;
		Path path = null;
		
		System.out.print(getMessage("benvenuto.txt"));
		
		while(!pathString.equals("exit")) {
			pathString = Gioco.input().strip();
			try {
				if(pathString.equals("default"))
					path = PATH_MONDO_DEFAULT;
				else
					path = Paths.get(pathString);

				m = Mondo.fromFile(path);
				System.err.println("\n\t\t\tCARICATO CON SUCCESSO!");
				break;
			}
			catch(FileNonEsistenteOVuotoException e) {
				System.out.println(e.getMessage());
			}
		}
		
		if(!pathString.equals("exit")) {
			System.out.println(getMessage("benvenuto2.txt"));
			
			pathString = Gioco.input().strip();
			
			while(true) {
				if(pathString.equals("no")) {
					g.play(m);
					break;
				}
				else {
					if(pathString.equals("default"))
						path = PATH_SCRIPT_DEFAULT;
					else 
						path = Paths.get(pathString);
					
					try {
						g.play(m, path);
						break;
					}
					catch(FileNonEsistenteOVuotoException e){
						System.out.println(e.getMessage());
						pathString = Gioco.input();
					}
				}
			}
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