package utilita.interfaccie;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;


import entita.Entita;
import entita.Mondo;
import entita.link.Link;
import entita.oggetto.Oggetto;
import entita.personaggio.Giocatore;
import entita.personaggio.Personaggio;
import entita.stanza.Stanza;
import utilita.eccezioni.ErroreCaricamentoException;
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
	/**
	 * Directory delle entita' concrete
	 */
	public static final String 	PATH_OGGETTI = "entita.oggetto.concreto.",
								PATH_PERSONAGGIO = "entita.personaggio.",
								PATH_LINK = "entita.link.concreto.",
								PATH_STANZA = "entita.stanza.";
	
	/**
	 * Tipo di entita
	 */
	public static final String 	WORLD = "world",
								PLAYER = "player",
								STANZA = "room",
								LINKS = "links",
								CHARACTERS = "characters",
								OBJECTS = "objects";
	
	/**
	 * Tag del file
	 */
	public static final String 	DESCR = "description",
								TAB = "\\t",
								P = ":";
	
	/**
	 * Linee nel file di entita specifiche, per rispettare il pattern
	 */
	public static final int LINEE_PLAYER = 2,
							LINEE_MONDO = 3;
	
	/**
	 * Dizionario delle funzioni per la creazione dell'entita
	 */
	public static final Map<String, CreationFunction> dizionario_funzioni = Map.of(LINKS, AnalizzaFile::creaLink,
																					CHARACTERS, AnalizzaFile::creaPersonaggio,
																					OBJECTS, AnalizzaFile::creaOggetto);																	


	/**
	 * Dizionario delle entita:<p>
	 * 1°- Chiave = tipo di entita; Set = entita specifica
	 */
	private static Map<String, Set<? extends Entita>> dizionario_entita;
	private static Set<Stanza> stanze;
	
	public static void main(String[] args) throws MondoFileException {
		analizzaLista(FilesMethod.lettura(Paths.get("resourse", "minizak.game")).orElse(null));
	}
	
	/**
	 * 
	 * @param lista
	 * @return
	 * @throws MondoFileException
	 */
	public static Mondo analizzaLista(List<String> lista) throws MondoFileException {
		dizionario_entita = new HashMap<>();
		
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
			if(!parte.get(0).endsWith("]")) throw new FormattazioneFileException("file, pattern non rispettato");
			
			nome = parte.get(0).replace("]", "");
			
			if(nome.contains(WORLD)) {
				if(parte.size() != LINEE_MONDO) throw new FormattazioneFileException("mondo");

				mondoString = parte;
				continue;
			}
			
			if(nome.contains(PLAYER)) {
				if(parte.size() != LINEE_PLAYER) throw new ErroreCaricamentoException("Attenzione! Sono presenti più giocatori!");
				
				Giocatore player = creaGiocatore(parte);
				continue;
			}
			
			if(nome.contains(STANZA)) {
				stanzeString.add(parte);
				continue;
			}
			
			//creazione delle altre entita in insiemi
			Set<? extends Entita> set = dizionario_funzioni.get(nome).apply(parte.subList(1, parte.size()));
			dizionario_entita.putIfAbsent(nome, set);
		}
			
		//creo il mondo e lo ritorno
		String nomeMondo = mondoString.get(0).split(P, 2)[1]; 
		String description = mondoString.get(1).split(TAB)[1];
		String start = mondoString.get(2).split(TAB)[1];
		stanze = creaStanze(stanzeString);

		Stanza stanzaStart = stanze.stream().filter(x -> x.getNome().equals(start)).findAny().orElseThrow(EntitaException::new);
		
		return new Mondo(nomeMondo, description, stanze, stanzaStart);
	}
	
	
	//METODI DI CREAZIONE STANZA E GIOCATORE
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


	
	//METODI DEL DIZIONARIO PER LA CREAZIONE DI INSIEMI DI ENTITA
	private static Set<Personaggio> creaPersonaggio(List<String> pattern) throws MondoFileException {
		Set<Personaggio> pers = new HashSet<>();
		List<String> supporto = new ArrayList<>();
		
		for(String personaggio : pattern) {
			supporto = Arrays.asList(personaggio.split(TAB));
 			System.out.println(supporto);
		}
		
		return pers;
	}

	private static Set<Oggetto> creaOggetto(List<String> pattern) throws MondoFileException {
		Set<Oggetto> oggetti = new HashSet<>();
		
		
		return oggetti;
	}
	
	private static Set<Link> creaLink(List<String> pattern) throws MondoFileException {
		Set<Link> links = new HashSet<>();
		
		return links;
	}

	//METODI DI SUPPORTO
	private static Entita creazione(String pathEntita) {
		
		
		return null;
	}
	
	public static <T extends Entita> Set<T> convertitore(Supplier<Set<T>> tipologia, Set<String> setString, String entita) throws EntitaException {
		Set<T> setEntita = tipologia.get();
		
		for(String entitaString : setString) 
			setEntita.add((T) dizionario_entita.get(entita).stream().filter(x -> x.getNome().equals(entitaString)).findAny().orElseThrow(EntitaException::new));
		
		return setEntita;
	}
}

