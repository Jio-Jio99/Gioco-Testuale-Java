package utilita.creazione;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;


import entita.Entita;
import entita.Mondo;
import entita.link.Link;
import entita.oggetto.Contenitore;
import entita.oggetto.Oggetto;
import entita.personaggio.Animale;
import entita.personaggio.Personaggio;
import entita.personaggio.concreto.Giocatore;
import entita.stanza.Stanza;
import entita.stanza.StanzaBuilder;
import utilita.creazione.eccezioni.ErroreCaricamentoException;
import utilita.creazione.eccezioni.GiocatoreException;
import utilita.creazione.eccezioni.MondoFileException;
import utilita.creazione.eccezioni.concreto.EntitaException;
import utilita.creazione.eccezioni.concreto.ErroreFileException;
import utilita.creazione.eccezioni.concreto.FormattazioneFileException;
import utilita.creazione.eccezioni.concreto.LinkFileException;
import utilita.creazione.eccezioni.concreto.NomeEsistenteException;
import utilita.creazione.eccezioni.concreto.PosizioneFileException;
import utilita.creazione.interfaccia.FilesMethod;
import utilita.creazione.interfaccia.Inventario;
import utilita.creazione.interfaccia.Observable;
import utilita.creazione.interfaccia.Observer;
import utilita.creazione.interfaccia.funzionali.CreationFunction;

/**
 * Classe astratta che analizza e crea il {@link Mondo}, controllando se i pattern nel file vengono rispettati
 * @author gioele
 *
 */
public abstract class AnalizzaFile implements Observable{
	/**
	 * Directory delle entita' concrete
	 */
	public static final String 	PATH_OGGETTI 		= "entita.oggetto.concreto.",
								PATH_PERSONAGGIO 	= "entita.personaggio.concreto.",
								PATH_LINK 			= "entita.link.concreto.",
								PATH_STANZA			= "entita.stanza.";
	
	/**
	 * Tipo di entita
	 */
	public static final String	WORLD		= "world",
								PLAYER		= "player",
								STANZA		= "room:",
								LINKS		= "links",
								CHARACTERS	= "characters",
								OBJECTS		= "objects";
		
	/**
	 * Tag del file
	 */
	public static final String 	DESCR 	= "description",
								TAB 	= "\\t",
								P 		= ":",
								S 		= "//";
	
	/**
	 * Linee nel file di entita specifiche, per rispettare il pattern
	 */
	public static final int LINEE_PLAYER = 2,
							LINEE_MONDO = 3,
							MIN_LINEE_STANZA = 3,
							
							PARTI_LINK = 4,
							PARTI_OG = 3;
							
	/**
	 * Posizione del nome della classe
	 */
	public static final int P_CLASSE = 1;

	/**
	 * Dizionario delle funzioni per la creazione dell'entita
	 */
	public static final Map<String, CreationFunction> dizionario_funzioni = Map.of(	LINKS, AnalizzaFile::creaLink,
																					CHARACTERS, AnalizzaFile::creaPersonaggio,
																					OBJECTS, AnalizzaFile::creaOggetto,
																					STANZA, AnalizzaFile::creaStanza,
																					PLAYER, AnalizzaFile::creaGiocatore);																	

	/**
	 * Dizionario delle entita:<p>
	 * 1°- Chiave = tipo di entita; Set = entita specifica
	 */
	private static Map<String, Map<String, Entita>> dizionario_entita;

	/**
	 * Lista degli osservatori
	 */
	private static List<Observer> osservatori = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		analizzaLista(FilesMethod.lettura(Paths.get("resourse", "minizak.game")));
	}

	/**
	 * Metodo che analizza il file del gioco e crea il mondo
	 * @param lista = Lista di String, dalla lettura del file
	 * @return {@link Mondo}
	 * @throws ErroreCaricamentoException = Eccezioni nella lettura
	 */
	public static Mondo analizzaLista(List<String> lista) throws ErroreCaricamentoException {
		dizionario_entita = new HashMap<>();
		
		if(lista.isEmpty())
			throw new ErroreFileException("File vuoto!");
		
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
		
		//variabili di supporto
		String nome = "";
		String nomeMondo = "";  
		String descriptionMondo = "";
		String start = ""; 
		
		for(List<String> parte : partizione) {
			if(!parte.get(0).endsWith("]")) throw new FormattazioneFileException("file, pattern non rispettato");
			
			nome = parte.get(0).replace("]", "");
			
			if(nome.contains(WORLD)) {
				if(parte.size() != LINEE_MONDO) throw new FormattazioneFileException("mondo");
				
				nomeMondo = parte.get(0).split(P, 2)[1].replace("]", "");
				descriptionMondo =  parte.get(1).split(TAB)[1];
				start = parte.get(2).split(TAB)[1];
				
				continue;
			}
			
			if(nome.contains(STANZA)) 
				nome = STANZA;
			else
				parte = parte.subList(1, parte.size());
			
			dizionario_funzioni.get(nome).apply(parte);
		}
		
		
		Stanza stanzaStart = (Stanza) convertitore(start);
		
		Giocatore.getInstance().setPosizione(stanzaStart);
		
		//NOTIFICA, converto le stringhe in oggetto
		for(Observer oss : osservatori)
			oss.converti();
		
		controllo();
		
		return new Mondo(nomeMondo, descriptionMondo, dizionario_entita.get(STANZA), stanzaStart);
	}
	
	
	//METODI DEL DIZIONARIO PER LA CREAZIONE DELL'ENTITA
	/**
	 * Metodo che instanzia il Giocatore
	 * @param pattern = List<String> del giocatore
	 * @return {@link Giocatore}
	 * @throws MondoFileException
	 */
	private static void creaGiocatore(List<String> pattern) throws MondoFileException {
		if(pattern.size() >= LINEE_PLAYER || pattern.size() <= 0) throw new FormattazioneFileException("player, sono presenti pi� giocatori!");
		
		String nomeGiocatore = pattern.get(0).split(TAB)[0];

		try {
			Giocatore.getInstance(nomeGiocatore.strip());
		} catch (GiocatoreException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo che costruisce una {@link Stanza} tramite la {@link StanzaBuilder}
	 * @param pattern = List<String> di una stanza
	 * @return {@link Stanza}
	 * @throws MondoFileException
	 */
	private static void creaStanza(List<String> pattern) throws MondoFileException {
		dizionario_entita.putIfAbsent(STANZA, new HashMap<>());
		
		String nome = pattern.get(0).split(P)[1].replace("]", "").strip();
		
		controlloNome(nome);
		
		String descrizione = pattern.get(1).split(TAB,2)[1];
		
		if(pattern.size() < MIN_LINEE_STANZA) throw new FormattazioneFileException("stanza " + nome);
		
		Map<String, String> opzioniStanza = pattern.subList(2, pattern.size()).stream().map(x -> x.split(TAB,2)).filter(x -> x.length == 2).collect(Collectors.toMap(x -> x[0], x -> x[1]));
		
		StanzaBuilder stanza = StanzaBuilder.creaStanzaBuilder(nome, descrizione);
		
		//ciclo che invoca i vari metodi della classe StanzaBuilder
		for(Map.Entry<String, String> m : opzioniStanza.entrySet()) {
			try {
				
				Method metodo = StanzaBuilder.class.getMethod(m.getKey(), String.class);
				metodo.invoke(stanza, m.getValue());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		dizionario_entita.get(STANZA).put(nome, stanza.build());
	}
	
	/**
	 * Metodo che costruisce una mappa di {@link Personaggio} instanziando i Personaggi concreti usando la reflection, con chiave il nome
	 * @param pattern = List<String> dei vari personaggi
	 * @return Map<String, {@link Personaggio}> personaggi 
	 * @throws MondoFileException
	 */
	private static void creaPersonaggio(List<String> pattern) throws MondoFileException {
		dizionario_entita.putIfAbsent(CHARACTERS, new HashMap<>());
		
		//variabili di supporto
		List<String> parti = new ArrayList<>();
		Class<?> classe = null;
		Constructor<?> constr = null;
		Personaggio p =  null;
		String nome = "";
		
		for(String personaggio : pattern) {
			parti = Arrays.asList(personaggio.split(TAB));
			parti.forEach(String::strip);
			
			if(parti.size() <= 0)
				throw new FormattazioneFileException("personaggio errato" +  parti);
			
			try {
				classe = Class.forName(PATH_PERSONAGGIO + parti.get(1));
				constr = classe.getConstructor(String.class);
				nome = parti.get(0);
				
				controlloNome(nome);
				
				p = (Personaggio) constr.newInstance(nome);
				
				if(!(p instanceof Animale) && parti.size() > 2) 
					p.setInventario(parti.subList(2,parti.size()).stream().collect(Collectors.toSet()));
				
			}
			catch(ClassNotFoundException e) {
				throw new EntitaException("Entita " + parti.get(1));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			inserimentoInMappa(CHARACTERS, p);
		}
	}
	
	/**
	 * Metodo che costruisce un set di {@link Oggetto} instanziando gli oggetti concreti usando la reflection
	 * @param pattern = List<String> di tutti gli oggetti
	 * @return Set<{@link Oggetti}> di Oggetto
	 * @throws MondoFileException
	 */
	private static void creaOggetto(List<String> pattern) throws MondoFileException {
		dizionario_entita.putIfAbsent(OBJECTS, new HashMap<>());
		
		//variabili di supporto
		List<String> parti = new ArrayList<>();
		Class<?> classe = null;
		Constructor<?> constr = null;
		Oggetto og = null;
		String classeString = "";
		String nome = "";
		
		for(String oggetto : pattern) {
			parti = Arrays.asList(oggetto.split(TAB));
			parti.forEach(String::strip);
			
			if(parti.size() > (PARTI_OG-1) && parti.size() < PARTI_OG) 
				throw new FormattazioneFileException("oggetto non corretto" + parti);
			
			try {
				classeString = PATH_OGGETTI;
				classeString += parti.get(1).split(" ")[0];

				classe = Class.forName(classeString);
				constr = classe.getConstructor(String.class);
				
				nome = parti.get(0);
				
				controlloNome(nome);
				
				og = (Oggetto) constr.newInstance(parti.get(0));

				if(og instanceof Contenitore && parti.size() == PARTI_OG) 
					((Contenitore)og).setInventario(parti.get(2));
			}
			catch(ClassNotFoundException e) {
				throw new EntitaException(classeString);
			} 
			catch(Exception e) {
				e.printStackTrace();
			}
			
			inserimentoInMappa(OBJECTS, og);
		}
	}
	
	/**
	 * Metodo che costruisce un set di {@link Link} instanziando i link concreti usando la reflection
	 * @param pattern = List<String> di tutti i Link
	 * @return Set<{@link Link} di link
	 * @throws MondoFileException
	 */
	private static void creaLink(List<String> pattern) throws MondoFileException {
		dizionario_entita.putIfAbsent(LINKS, new HashMap<>());
		//variabili di supporto
		List<String> parti = new ArrayList<>();
		Class<?> classe = null;
		Constructor<?> constr = null;
		Link l = null;
		String nome = "";
		
		for(String link : pattern) {
			parti = Arrays.asList(link.split(TAB));
			parti.forEach(String::strip);
			
			if(parti.size() != PARTI_LINK) 
				throw new FormattazioneFileException("link non corretto");
			
			try {
				classe = Class.forName(PATH_LINK + parti.get(P_CLASSE));
				constr = classe.getConstructor(String.class, String.class, String.class);
				
				nome = parti.get(0);
				controlloNome(nome);
				
				l = (Link) constr.newInstance(nome, parti.get(2), parti.get(3));
			}
			catch(ClassNotFoundException e) {
				throw new EntitaException(parti.get(P_CLASSE));
			} 
			catch(Exception e) {
				e.printStackTrace();
			}
			
			inserimentoInMappa(LINKS, l);;
		}		
	}

	//METODI DI SUPPORTO
	/**
	 * Metodo che inserisce la nuova entita creata nella mappa e nella lista degli osservatori
	 * @param key
	 * @param e
	 */
	private static void inserimentoInMappa(String key, Entita e) {
		osservatori.add((Observer) e);
		dizionario_entita.get(key).put(e.getNome(), e);
	}
	
	/**
	 * Metodo che preso in input una stringa, corrispondente al nome di un entita, ne restituisce l'instanza creata, altrimenti lancia l'eccezione di non esistenza
	 * @param nomeEntita = String
	 * @return {@link Entita}
	 * @throws EntitaException
	 */
	public static Entita convertitore(String nomeEntita) throws EntitaException {
		return dizionario_entita.values().stream().filter(x -> x.containsKey(nomeEntita)).map(x -> x.get(nomeEntita)).findAny().orElseThrow(() -> new EntitaException(nomeEntita));
	}
	
	//FUNZIONI DI CONTROLLO
	/**
	 * Metodo che prese tutte le stanze controlla se tutti gli oggetti/personaggi rispettano le condizioni per la creazione del mondo, lanciando altrimenti l'eccezione corrispondente all'errore: <p>
	 * - {@link PosizioneFileException} <p>
	 * - {@link LinkFileException} <p>
	 * - {@link ErroreFileException}
	 * @param stanze = Set<Stanza>
	 * @throws ErroreCaricamentoException
	 */
	private static void controllo() throws ErroreCaricamentoException{
		Stanza stanza = null;
		List<Entita> doppioni = new ArrayList<>();
		List<Personaggio> personaggi = new ArrayList<>();
		List<Entita> verifica = new ArrayList<>();
		List<Inventario> verificaInventario = new ArrayList<>();
		Set<Personaggio> personaggiStanza = new HashSet<>();
		Set<Oggetto> oggettiStanza = new HashSet<>();

		
		for(Entita s : dizionario_entita.get(STANZA).values()) {
			stanza = (Stanza) s;
			
			personaggiStanza = stanza.getPersonaggi().values().stream().collect(Collectors.toSet());
			oggettiStanza = stanza.getInventario().values().stream().collect(Collectors.toSet());
			
			doppioni.addAll(personaggiStanza);
			doppioni.addAll(oggettiStanza);

			//Controllo se gli accessi alle stanze sono coerenti con i link
			for(Link l : stanza.getAccessi().values()) 
				if(!l.connected(stanza))
					throw new LinkFileException(l.getNome());
			
			//Controllo la posizione degli oggetti se siano coerenti con l'inventario dei personaggi nelle stanze
			
			verificaInventario = personaggiStanza.stream().flatMap(x -> x.getInventario().values().stream()).filter(x -> !(x instanceof Animale)).collect(Collectors.toList());
		
			if(!oggettiStanza.containsAll(verificaInventario))
				throw new ErroreFileException("oggetto di un personaggio nella stanza non presente anche nella stanza " + stanza);			
			
			personaggiStanza.clear();
		}
		
		//Controllo se � presente un personaggio/oggetto in pi� stanze
		verifica.addAll(doppioni.stream().filter(x -> Collections.frequency(doppioni, x) >=2).collect(Collectors.toList()));			
		
		if(!verifica.isEmpty())
			throw new PosizioneFileException(verifica.toString());
	}
	
	/**
	 * Metodo privato che controlla se non ci sono ripetizioni di nomi tra le entita create, altrimenti lancia l'eccezione
	 * @throws NomeEsistenteException
	 */
	private static void controlloNome(String nome) throws NomeEsistenteException {
		if(dizionario_entita.values().stream().flatMap(x -> x.keySet().stream()).anyMatch(x -> x.equals(nome)))
			throw new NomeEsistenteException(nome);
	}
	
	//METODI PER GESTIRE GLI OBSERVER
	public void registraObserver(Observer o) {
		osservatori.add(o);		
	}

	public void cancellaObserver(Observer o) {
		osservatori.remove(o);
	}

	public void notifica() throws EntitaException {
		for(Observer oss : osservatori)
			oss.converti();
	}
}

