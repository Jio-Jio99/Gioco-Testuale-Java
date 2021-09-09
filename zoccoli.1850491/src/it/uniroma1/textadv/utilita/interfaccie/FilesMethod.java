package it.uniroma1.textadv.utilita.interfaccie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Interfaccia di soli metodi statici, utili per la lettura e scrittura su File
 * @author gioele
 */
public interface FilesMethod {
	
	//METODI DI LETTURA DA FILE
	/**
	 * Metodo che prende una stringa in input che corrisponde al percorso del file, lo trasforma in un oggetto {@link Path} e chiama il metodo lettura
	 * che prende in input un path.
	 * Letto il file restituisce un Optional contenente la lista di stringhe del file
	 * @param fileName = percorso del file
	 * @return {@link Optional}
	 */
	public static ArrayList<String> lettura(String fileName) {
		return lettura(Paths.get(fileName));
	}
	
	/**
	 * Metodo che prende in input un Path di un file e restitusce un Optional contenente la lista di String del file
	 * @param fileName = {@link Path}
	 * @return {@link Optional}
	 */
	public static ArrayList<String> lettura(Path fileName) {
		
		try(BufferedReader br = Files.newBufferedReader(fileName)){
			return br.lines().collect(Collectors.toCollection(ArrayList::new));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<>();
	}
	
	
	//METODI DI SCRITTURA IN FILE
	/**
	 * Overloading del metodo 'salva' che apre di default in WRITE il file in Scrittura
	 * @param fileName = String
	 * @param daSalvare = Array di String
	 */
	public static void salva(String fileName, String... daSalvare) {
		salva(fileName, StandardOpenOption.WRITE, daSalvare);
	}
	
	/**
	 * Overloading del metodo 'salva' che apre di default in WRITE il file in Scrittura
	 * @param fileName = Path
	 * @param daSalvare = Array di String
	 */
	public static void salva(Path fileName, String... daSalvare) {
		salva(fileName, StandardOpenOption.WRITE, daSalvare);
	}
	
	/**
	 * Overloading del metodo 'salva' che trasforma lo String del file ricevuto e lo trasforma in {@link Path}
	 * @param fileName = String
	 * @param tipoApertura = {@link StandardOpenOption}
	 * @param daSalvare = Array di String
	 */
	public static void salva(String fileName,  StandardOpenOption tipoApertura, String... daSalvare) {
		salva(Paths.get(fileName), tipoApertura, daSalvare);
	}
	
	/**
	 * Metodo che preso in input un {@link Path} di un file ed un Array di String da salvare nel file, aprendo il file
	 * a seconda dello {@link StandardOpenOption} ricevuto
	 * @param fileName = {@link Path}
	 * @param tipoApertura = {@link StandardOpenOption}
	 * @param daSalvare = Array di String
	 */
	public static void salva(Path fileName, StandardOpenOption tipoApertura ,String... daSalvare) {
		try(BufferedWriter wr = Files.newBufferedWriter(fileName, tipoApertura)){
			for(String s : daSalvare)
				wr.write(s + "\n");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
