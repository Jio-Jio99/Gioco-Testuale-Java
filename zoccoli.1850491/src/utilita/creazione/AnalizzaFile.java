package utilita.creazione;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;


import entita.Entita;
import entita.Mondo;
import entita.link.Link;
import entita.link.concreto.Libero;
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
import utilita.creazione.eccezioni.concreto.GiocatoreNonInstanziatoException;
import utilita.creazione.eccezioni.concreto.LinkFileException;
import utilita.creazione.eccezioni.concreto.NomeEsistenteException;
import utilita.creazione.eccezioni.concreto.PosizioneFileException;
import utilita.creazione.interfaccie.Inventario;
import utilita.creazione.interfaccie.Observable;
import utilita.creazione.interfaccie.Observer;
import utilita.creazione.interfaccie.funzionali.CreationFunction;

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
								STANZA		= "room",
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
																					OBJECTS, AnalizzaFile::creaOggetto);																	

	/**
	 * Dizionario delle entita:<p>
	 * 1°- Chiave = tipo di entita; Set = entita specifica
	 */
	private static Map<String, Set<? extends Entita>> dizionario_entita;

	/**
	 * Lista degli osservatori
	 */
	private static List<Observer> osservatori = new ArrayList<>();

	/**
	 * Metodo che analizza il file del gioco e crea il mondo
	 * @param lista = Lista di String, dalla lettura del file
	 * @return {@link Mondo}
	 * @throws ErroreCaricamentoException = Eccezioni nella lettura
	 */
	public static Mondo analizzaLista(List<String> lista) throws ErroreCaricamentoException {
		dizionario_entita = new HashMap<>();
		Set<Stanza> stanze = new HashSet<>();
		
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
		
		String nome = "";
		List<String> mondoString = new ArrayList<>();
		Stanza stanza = null;
		Giocatore player = null;
		
		for(List<String> parte : partizione) {
			if(!parte.get(0).endsWith("]")) throw new FormattazioneFileException("file, pattern non rispettato");
			
			nome = parte.get(0).replace("]", "");
			
			if(nome.contains(WORLD)) {
				if(parte.size() != LINEE_MONDO) throw new FormattazioneFileException("mondo");
				
				mondoString = parte;
				continue;
			}
			
			if(nome.contains(PLAYER)) {
				if(parte.size() != LINEE_PLAYER) throw new ErroreFileException("Attenzione! Sono presenti più giocatori!");
				player = creaGiocatore(parte);
				continue;
			}
			
			if(nome.contains(STANZA)) {
				stanza = creaStanza(parte);
				osservatori.add(stanza);
				stanze.add(stanza);
				continue;
			}
			//creazione delle altre entita in insiemi
			Set<? extends Entita> set = dizionario_funzioni.get(nome).apply(parte.subList(1, parte.size()));
			dizionario_entita.putIfAbsent(nome, set);
		}

		if(player == null) 
			throw new GiocatoreNonInstanziatoException();
		
		//creo il mondo e lo ritorno
		String nomeMondo = mondoString.get(0).split(P, 2)[1].replace("]", ""); 
		String description = mondoString.get(1).split(TAB)[1];
		String start = mondoString.get(2).split(TAB)[1];
		
		dizionario_entita.put(STANZA, stanze);
		
		Stanza stanzaStart = (Stanza) convertitore(start);
		
		player.setPosizione(stanzaStart);
		
		controlloNomi();
		
		//NOTIFICA, converto le stringhe in oggetto
		for(Observer oss : osservatori)
			oss.converti();
		
		controllo(stanze);
		
		return new Mondo(nomeMondo, description, stanze, stanzaStart);
	}
	
	
	//METODI DI CREAZIONE STANZA E GIOCATORE
	/**
	 * Metodo che instanzia il Giocatore
	 * @param pattern = List<String> del giocatore
	 * @return {@link Giocatore}
	 * @throws MondoFileException
	 */
	private static Giocatore creaGiocatore(List<String> pattern) throws MondoFileException {
		
		if(pattern.size() > 2 || pattern.size() <= 0) throw new FormattazioneFileException("player");
		
		String nomeGiocatore = pattern.get(1).split("\\s")[0];
		
		try {
			return  Giocatore.getInstance(nomeGiocatore.strip());
		} catch (GiocatoreException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//METODI DEL DIZIONARIO PER LA CREAZIONE DI INSIEMI DI ENTITA
	/**
	 * Metodo che costruisce un set di {@link Personaggio} instanziando i Personaggi concreti usando la reflection
	 * @param pattern = List<String> dei vari personaggi
	 * @return Set<{@link Personaggio}> personaggi 
	 * @throws MondoFileException
	 */
	private static Set<Personaggio> creaPersonaggio(List<String> pattern) throws MondoFileException {
		Set<Personaggio> pers = new HashSet<>();
		
		//variabili di supporto
		List<String> parti = new ArrayList<>();
		Class<?> classe = null;
		Constructor<?> constr = null;
		Personaggio p =  null;
		
		for(String personaggio : pattern) {
			parti = Arrays.asList(personaggio.split(TAB));
			parti.forEach(String::strip);
			
			if(parti.size() <= 0)
				throw new FormattazioneFileException("personaggio errato" +  parti);
			
			try {
				classe = Class.forName(PATH_PERSONAGGIO + parti.get(1));
				constr = classe.getConstructor(String.class);

				p = (Personaggio) constr.newInstance(parti.get(0));
				
				if(!(p instanceof Animale) && parti.size() > 2) 
					p.setInventario(parti.subList(2,parti.size()).stream().collect(Collectors.toSet()));
				
			}
			catch(ClassNotFoundException e) {
				throw new EntitaException("Entita " + parti.get(1));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			osservatori.add(p);
			pers.add(p);
		}
		
		
		return pers;
	}
	
	/**
	 * Metodo che costruisce una {@link Stanza} tramite la {@link StanzaBuilder}
	 * @param pattern = List<String> di una stanza
	 * @return {@link Stanza}
	 * @throws MondoFileException
	 */
	private static Stanza creaStanza(List<String> pattern) throws MondoFileException {
		String nome = pattern.get(0).split(P)[1].replace("]", "").strip();
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
		
		return stanza.build();
	}
	
	/**
	 * Metodo che costruisce un set di {@link Oggetto} instanziando gli oggetti concreti usando la reflection
	 * @param pattern = List<String> di tutti gli oggetti
	 * @return Set<{@link Oggetti}> di Oggetto
	 * @throws MondoFileException
	 */
	private static Set<Oggetto> creaOggetto(List<String> pattern) throws MondoFileException {
		Set<Oggetto> oggetti = new HashSet<>();
		
		//variabili di supporto
		List<String> parti = new ArrayList<>();
		Class<?> classe = null;
		Constructor<?> constr = null;
		Oggetto og = null;
		Contenitore con = null;
		String classeString = "";
		
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
				og = (Oggetto) constr.newInstance(parti.get(0));

				if(og instanceof Contenitore && parti.size() == PARTI_OG) {
					con = (Contenitore) og;
					con.setInventario(parti.get(2));
				}
			}
			catch(ClassNotFoundException e) {
				throw new EntitaException(classeString);
			} 
			catch(Exception e) {
				e.printStackTrace();
			}
			
			osservatori.add(og);
			oggetti.add(og);
		}

		return oggetti;
	}
	
	/**
	 * Metodo che costruisce un set di {@link Link} instanziando i link concreti usando la reflection
	 * @param pattern = List<String> di tutti i Link
	 * @return Set<{@link Link} di link
	 * @throws MondoFileException
	 */
	private static Set<Link> creaLink(List<String> pattern) throws MondoFileException {
		Set<Link> links = new HashSet<>();
		
		//variabili di supporto
		List<String> parti = new ArrayList<>();
		Class<?> classe = null;
		Constructor<?> constr = null;
		Link l = null;
		
		for(String link : pattern) {
			parti = Arrays.asList(link.split(TAB));
			parti.forEach(String::strip);
			
			if(parti.size() != PARTI_LINK) 
				throw new FormattazioneFileException("link non corretto");
			
			try {
				classe = Class.forName(PATH_LINK + parti.get(P_CLASSE));
				constr = classe.getConstructor(String.class, String.class, String.class);
				l = (Link) constr.newInstance(parti.get(0), parti.get(2), parti.get(3));
			}
			catch(ClassNotFoundException e) {
				throw new EntitaException(parti.get(P_CLASSE));
			} 
			catch(Exception e) {
				e.printStackTrace();
			}
			
			osservatori.add(l);
			links.add(l);
		}		
		return links;
	}

	//METODI DI SUPPORTO
	/**
	 * Metodo che preso in input una stringa, corrispondente al nome di un entita, ne restituisce l'instanza creata, altrimenti lancia l'eccezione di non esistenza
	 * @param nomeEntita = String
	 * @return {@link Entita}
	 * @throws EntitaException
	 */
	public static Entita convertitore(String nomeEntita) throws EntitaException {
		return dizionario_entita.values().stream().flatMap(Set::stream).filter(x -> x.getNome().equals(nomeEntita.strip())).findAny().orElseThrow(() -> new EntitaException(nomeEntita));
	}
	
	//FUNZIONI DI CONTROLLO
	/**
	 * Metodo che preso il set di stanze controlla se tutti gli oggetti/personaggi rispettano le condizioni per la creazione del mondo, lanciando altrimenti l'eccezione corrispondente all'errore: <p>
	 * - {@link PosizioneFileException} <p>
	 * - {@link LinkFileException} <p>
	 * - {@link ErroreFileException}
	 * @param stanze = Set<Stanza>
	 * @throws ErroreCaricamentoException
	 */
	private static void controllo(Set<Stanza> stanze) throws ErroreCaricamentoException{
		//Controllo se � presente un personaggio/oggetto in pi� stanze
		List<Entita> lista = stanze.stream()
									.map(AnalizzaFile::supportoControllo)
									.flatMap(Set::stream)
									.collect(Collectors.toList());
		
		Set<Entita> p = lista.stream().filter(x -> Collections.frequency(lista, x) >= 2).collect(Collectors.toSet());

		if(!p.isEmpty())
			throw new PosizioneFileException(p.toString());
		
		//variabili di supporto
		Set<Link> accessi = new HashSet<>();
		List<Link> linkInstanziati = dizionario_entita.get(LINKS).stream().map(x -> (Link)x).collect(Collectors.toCollection(ArrayList::new));
		int controllo = 0;
		
		Set<Oggetto> oggetti = new HashSet<>();
		Set<Inventario> inventarioPersonaggi = new HashSet<>();

		
		for(Stanza s : stanze) {
		//Controllo se gli accessi alle stanze sono coerenti con i link

			accessi = s.getAccessi().values().stream().collect(Collectors.toSet());
			
			for(Link l : linkInstanziati) {
				if(accessi.contains(l) && l.connected(s)) 
					controllo++;
			}
			
			controllo += accessi.stream().filter(x-> x instanceof Libero).count();
			
			if(controllo != accessi.size()) 
				throw new LinkFileException(s.toString());
			
		//Controllo la posizione degli oggetti se siano coerenti con l'inventario dei personaggi nelle stanze
			oggetti = s.getInventario();
			s.getPersonaggi().forEach(x -> inventarioPersonaggi.addAll(x.getInventario()));
			
			for(Inventario in : inventarioPersonaggi) {
				if(in instanceof Animale) 
					continue;
				else if(!oggetti.contains(in)) 
					throw new ErroreFileException("oggetto di un personaggio non presente anche nella stanza " + in);
			}
			
			inventarioPersonaggi.clear();
			controllo = 0;
		}
	}
	
	/**
	 * Metodo privato che controlla se non ci sono ripetizioni di nomi tra le entita create, altrimenti lancia l'eccezione
	 * @throws NomeEsistenteException
	 */
	private static void controlloNomi() throws NomeEsistenteException {
		List<String> lista = dizionario_entita.values()	.stream()
														.flatMap(Set::stream)
														.map(x -> x.getNome())
														.collect(Collectors.toList());
		
		List<String> lista2 = lista.stream().filter(x -> Collections.frequency(lista, x) >= 2).collect(Collectors.toList());
		
		if(!lista2.isEmpty())
			throw new NomeEsistenteException(lista2.toString());
	}
	
	
	/**
	 * Metodo privato che prende i personaggi e gli oggetti in una stanza e l'inserisce in un unico set
	 * @param x = Stanza
	 * @return
	 */
	private static Set<Entita> supportoControllo(Stanza x){
		Set<Entita> set = new HashSet<>();
		
		set.addAll(x.getInventario());
		set.addAll(x.getPersonaggi());
		
		return set;
	}
	//METODI PER GESTIRE GLI OBSERVER
	/**
	 * Metodo che registra gli obsever dalla lista
	 */
	public void registraObserver(Observer o) {
		osservatori.add(o);		
	}
	
	/**
	 * Metodo che cancella gli observer dalla lista
	 */
	public void cancellaObserver(Observer o) {
		osservatori.remove(o);
	}
	
	/**
	 * Metodo che notifica cambiamenti a tutti gli observer
	 */
	public void notifica() throws EntitaException {
		for(Observer oss : osservatori)
			oss.converti();
	}
}

