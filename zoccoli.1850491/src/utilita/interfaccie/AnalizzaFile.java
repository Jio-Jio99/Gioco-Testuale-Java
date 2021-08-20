package utilita.interfaccie;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


import entita.Entita;
import entita.Mondo;
import entita.link.Link;
import entita.oggetto.Oggetto;
import entita.personaggio.Giocatore;
import entita.personaggio.Personaggio;
import entita.stanza.Stanza;
import utilita.eccezioni.GiocatoreException;
import utilita.eccezioni.MondoFileException;
import utilita.eccezioni.concreto.EntitaException;
import utilita.eccezioni.concreto.FormattazioneFileException;
import utilita.interfaccie.funzionali.CreationFunction;

/**
 * @author gioele
 *
 */
public abstract class AnalizzaFile {
	private static final String PATH_OGGETTI = "entita.oggetto.concreto.";
	private static final String PATH_PERSONAGGIO = "entita.personaggio.";
	private static final String PATH_LINK = "entita.link.concreto.";
	private static final String PATH_STANZA = "entita.stanza.";
	
	private static final String WORLD = "world";
	private static final String PLAYER = "player";
	private static final String STANZA = "room";
	private static final String LINKS = "links";
	private static final String CHARACTERS = "characters";
	private static final String OBJECTS = "objects";


	/**
	 * Dizionario delle funzioni per la creazione dell'entita
	 */
	private static final Map<String, CreationFunction> dizionario_funzioni = Map.of(LINKS, AnalizzaFile::creaLink,
																					CHARACTERS, AnalizzaFile::creaPersonaggio,
																					OBJECTS, AnalizzaFile::creaOggetto);																	


	
	public static void main(String[] args) throws MondoFileException {
		analizzaLista(FilesMethod.lettura(Paths.get("resourse", "minizak.game")).orElse(null));
	}
	
	/**
	 * Dizionario delle entita di due livelli:<p>
	 * 1°- Chiave = tipo di entita; Valore = dizionario di entita specifico <p>
	 * 2°- Chiave = nome dell'entita; Valore = entita creata
	 */
	private static Map<String, Set<Entita>> dizionario_entita;
	
			
	public static Mondo analizzaLista(List<String> lista) throws MondoFileException {
		/**
		 * Unisco tutte le linee splittate sul carattere \n durante la lettura del file, perch� le divider� tramite il carattere [
		 * ed eliminer� evenuntuali linee vuote nel file
		 */
		lista = Arrays.asList(lista.stream().filter(Predicate.not(String::isBlank))
											.map(String::strip)
											.reduce("", (x,y) -> x + y + "\n")
											.split("\\[")
							  );

		List<List<String>> partizione = lista.stream().filter(Predicate.not(String::isBlank))
													  .map(x -> Arrays.asList(x.split("\\n+")))
												 	  .collect(Collectors.toList());
		
		String nome = "";
		Set<List<String>> stanzeString = new HashSet<>();
		List<String> mondoString = new ArrayList<>();
		
		for(List<String> parte : partizione) {
			if(!parte.get(0).endsWith("]")) throw new FormattazioneFileException("/nel pattern non rispettato");
			
			nome = parte.get(0).replace("]", "");
			
			if(nome.contains(WORLD)) {
				mondoString = parte;
				continue;
			}
			
			if(nome.contains(PLAYER)) {
				Giocatore player = creaGiocatore(parte);
				continue;
			}
			
			if(nome.contains(STANZA)) {
				stanzeString.add(parte);
				continue;
			}

			Set<Entita> set = dizionario_funzioni.get(nome).apply(parte.subList(1, parte.size()));
			dizionario_entita.merge(nome, set, (x,y) -> {x.addAll(set); return x;});
		}
			
		//creo il mondo e lo ritorno
		if(mondoString.size() != 3) throw new FormattazioneFileException("nel mondo");
		
		String nomeMondo = mondoString.get(0).split(":", 2)[1].replace("]", ""); //parte del nome
		String description = mondoString.get(1).split("\\t",2)[1];
		String start = mondoString.get(2).split("\\t")[1];
		Set<Stanza> stanze = creaStanze(stanzeString);
		
		//return new Mondo(nomeMondo, description, stanze, stanze.stream().filter(x -> x.getNome().equals(start)).findAny().orElse(null));
		return null;
	}
	
	
	//METODI DI CREAZIONE
	private static Set<Stanza> creaStanze(Set<List<String>> pattern) throws MondoFileException {
		
		return null;
	}
	
	private static Giocatore creaGiocatore(List<String> pattern) throws MondoFileException {
		
		if(pattern.size() > 2 || pattern.size() <= 0) throw new FormattazioneFileException("player");
		
		String nomeGiocatore = pattern.get(1).split("\\s")[0];
		
		try {
			return  Giocatore.getInstance(nomeGiocatore);
		} catch (GiocatoreException e) {
			e.printStackTrace();
		}
		
		return null;
	}


	
	//METODI DIZIONARIO
	private static Set<Entita> creaPersonaggio(List<String> pattern) throws MondoFileException {

		return null;
	}

	private static Set<Entita> creaOggetto(List<String> pattern) throws MondoFileException {

		return null;
	}
	
	private static Set<Entita> creaLink(List<String> pattern) throws MondoFileException {
		
		return null;
	}

	//METODI DI SUPPORTO
	private static Entita creazione(String pathEntita) {
		
		
		return null;
	}
}
