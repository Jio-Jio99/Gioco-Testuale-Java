package utilita.interfaccie;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import entita.Entita;
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

public interface AnalizzaFile {
	String PATH_OGGETTI = "entita.oggetto.concreto.";
	String PATH_PERSONAGGIO = "entita.personaggio.";
	String PATH_LINK = "entita.link.concreto.";
	String PATH_STANZA = "entita.stanza.";

	Map<String, CreationFunction> dizionario_funzioni =
												Map.of(	"room", AnalizzaFile::creaStanza,
														"objects", AnalizzaFile::creaOggetto,
														"links", AnalizzaFile::creaLink ,
														"player", AnalizzaFile::creaGiocatore,
														"characters", AnalizzaFile::creaPersonaggio,
														"world", AnalizzaFile::creaMondo);
	
	public static void main(String[] args) throws MondoFileException {
		analizzaLista(FilesMethod.lettura(Paths.get("resourse", "minizak.game")).orElse(null));
	}
			
	public static void analizzaLista(List<String> lista) throws MondoFileException {
		/**
		 * Unisco tutte le linee splittate sul carattere \n durante la lettura del file, perché le dividerò tramite il carattere [
		 * ed eliminerò evenuntuali linee vuote nel file
		 */
		lista = Arrays.asList(lista.stream().filter(Predicate.not(String::isBlank))
											.map(String::strip)
											.reduce("", (x,y) -> x + y + "\n")
											.split("\\[")
							  );

		List<List<String>> partizione = lista.stream().filter(Predicate.not(String::isBlank))
													  .map(x -> Arrays.asList(x.split("\\n+")))
												 	  .collect(Collectors.toList());
		
		/**
		 * lavoro su ogni parte del file
		 */
		String tipologia = "";
		String[] entita = new String[2];
		
		for(List<String> parte : partizione) {
			if(!parte.get(0).endsWith("]")) throw new FormattazioneFileException("/nel pattern non rispettato");

			tipologia = parte.get(0).replace("]", "");
			entita = tipologia.split(":",2);
			
			if(!dizionario_funzioni.containsKey(entita[0])) throw new EntitaException();
			
			dizionario_funzioni.get(entita[0]).apply(parte);
		}
			
		
		
	}
	
	private static Oggetto creaOggetto(List<String> pattern) throws MondoFileException {

		
		return null;
	}

	private static Oggetto creaMondo(List<String> pattern) throws MondoFileException {

		
		return null;
	}
	
	private static Stanza creaStanza(List<String> pattern) throws MondoFileException {
		
		
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
	
	private static Personaggio creaPersonaggio(List<String> pattern) throws MondoFileException {

		
		return null;
	}
	
	
	private static Link creaLink(List<String> pattern) throws MondoFileException {
		
		
		return null;
	}
	
	private static Entita creazione(String pathEntita) {
		
		
		
		
		return null;
	}
}
