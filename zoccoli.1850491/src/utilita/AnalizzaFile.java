package utilita;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
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
import utilita.eccezioni.ErroreCaricamentoException;
import utilita.eccezioni.GiocatoreException;
import utilita.eccezioni.MondoFileException;
import utilita.eccezioni.concreto.EntitaException;
import utilita.eccezioni.concreto.ErroreFileException;
import utilita.eccezioni.concreto.FormattazioneFileException;
import utilita.eccezioni.concreto.GiocatoreNonInstanziatoException;
import utilita.eccezioni.concreto.PosizioneFileException;
import utilita.interfaccie.FilesMethod;
import utilita.interfaccie.funzionali.CreationFunction;
import utilita.interfaccie.tag.Inventario;
import utilita.interfaccie.tag.Observable;
import utilita.interfaccie.tag.Observer;

/**
 * Classe astratta che analizza e crea il {@link Mondo}, controllando se i pattern nel file vengono rispettati
 * @author gioele
 *
 */
public abstract class AnalizzaFile implements Observable{
	public static void main(String[] args) throws ErroreCaricamentoException {
		analizzaLista(FilesMethod.lettura(Paths.get("resourse", "minizak.game")).orElse(null));
	}
	
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
	 * 1Â°- Chiave = tipo di entita; Set = entita specifica
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
	 * @throws MondoFileException = Eccezioni nella lettura
	 */
	public static Mondo analizzaLista(List<String> lista) throws ErroreCaricamentoException {
		dizionario_entita = new HashMap<>();
		Set<Stanza> stanze = new HashSet<>();

		
		/**
		 * Unisco tutte le linee splittate sul carattere \n durante la lettura del file, perchï¿½ le dividerï¿½ tramite il carattere [
		 * ed eliminerï¿½ evenuntuali linee vuote nel file
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
				if(parte.size() != LINEE_PLAYER) throw new ErroreFileException("Attenzione! Sono presenti piÃ¹ giocatori!");
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
		String nomeMondo = mondoString.get(0).split(P, 2)[1]; 
		String description = mondoString.get(1).split(TAB)[1];
		String start = mondoString.get(2).split(TAB)[1];
		
		dizionario_entita.put(STANZA, stanze);
		
		Stanza stanzaStart = (Stanza) convertitore(start);
		
		player.setPosizione(stanzaStart);
		
		//NOTIFICA, converto le stringhe in oggetto
		for(Observer oss : osservatori)
			oss.converti();
		
		controllo(stanze);
		
		return new Mondo(nomeMondo, description, stanze, stanzaStart);
	}
	
	
	//METODI DI CREAZIONE STANZA E GIOCATORE
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
	
	private static Stanza creaStanza(List<String> pattern) throws MondoFileException {
		String nome = pattern.get(0).split(P)[1].replace("]", "");
		String descrizione = pattern.get(1).split(TAB,2)[1];
		
		if(pattern.size() < MIN_LINEE_STANZA) throw new FormattazioneFileException("stanza " + nome);
		
		Map<String, String> opzioniStanza = pattern.subList(2, pattern.size()).stream().map(x -> x.split(TAB,2)).filter(x -> x.length == 2).collect(Collectors.toMap(x -> x[0], x -> x[1]));

		StanzaBuilder stanza = StanzaBuilder.creaStanzaBuilder(nome.strip(), descrizione);

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
	 * Metodo che preso in input una stringa, corrispondente al nome di un entita, ne restituisce l'instanza creata, altrimenti se non trovata, lancia l'eccezione di non esistenza
	 * @param nomeEntita = String
	 * @return {@link Entita}
	 * @throws EntitaException
	 */
	public static Entita convertitore(String nomeEntita) throws EntitaException {
		return dizionario_entita.values().stream().flatMap(Set::stream).filter(x -> x.getNome().equals(nomeEntita.strip())).findAny().orElseThrow(() -> new EntitaException(nomeEntita));
	}
	
	//FUNZIONI DI CONTROLLO
	private static void controllo(Set<Stanza> stanze) throws ErroreCaricamentoException{
		//Controllo se è presente un personaggio/oggetto in più stanze
		List<Entita> lista = stanze.stream()
									.map(AnalizzaFile::supportoControllo)
									.flatMap(Set::stream)
									.collect(Collectors.toList());
		
		Set<Entita> p = lista.stream().filter(x -> Collections.frequency(lista, x) >= 2).collect(Collectors.toSet());

		if(!p.isEmpty())
			throw new PosizioneFileException(p.toString());

		//2- Link corretti TODO
		
		//3- Posizione degli oggetti. Se sono nella stessa stanza di chi appartengono (non per i personaggi-inventario, ex Neo)
//		List<List<Entita>> oggettiEpersonaggi = new ArrayList<>();
//		Set<Oggetto> personaggioInventario = new HashSet<>();
//		
//		for(Stanza s : stanze) {
//			for(Oggetto in : s.getInventario()) {
//				personaggioInventario = s.getInventario();
//			}
//		}
	}
	
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

