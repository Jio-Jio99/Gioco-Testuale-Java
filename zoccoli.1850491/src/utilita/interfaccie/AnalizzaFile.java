package utilita.interfaccie;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import entita.Entita;
import entita.link.Link;
import entita.oggetto.Oggetto;
import entita.personaggio.Personaggio;
import entita.personaggio.concreto.Giocatore;
import entita.stanza.Stanza;
import utilita.eccezioni.EntitaException;
import utilita.eccezioni.FormattazioneFileException;
import utilita.eccezioni.GiocatoreException;
import utilita.eccezioni.MondoFileException;

public interface AnalizzaFile {
	String PATH_OGGETTI = "entita.oggetto.concreto.";
	String PATH_PERSONAGGIO = "entita.personaggio.concreto.";
	String PATH_LINK = "entita.link.concreto.";
	String PATH_STANZA = "entita.stanza.";

	Map<String, Function<List<String>, Entita>> dizionario_funzioni =
												Map.of(	"room", AnalizzaFile::creaStanza,
														"objects", AnalizzaFile::creaOggetto,
														"links", AnalizzaFile::creaLink ,
														"player", x -> creaGiocatore(x.get(0)),
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
			if(!parte.get(0).endsWith("]")) throw new FormattazioneFileException();

			tipologia = parte.get(0).replace("]", "");
			entita = tipologia.split(":",2);
			
			if(!dizionario_funzioni.containsKey(entita[0])) throw new EntitaException();
			
			dizionario_funzioni.get(entita[0]).apply(parte.subList(1, parte.size()));
		}
			
		
		
	}
	
	private static Oggetto creaOggetto(List<String> testo) {

		
		return null;
	}

	private static Oggetto creaMondo(List<String> testo) {

		
		return null;
	}
	
	private static Stanza creaStanza(List<String> testo) {
		
		
		return null;
	}
	
	private static Giocatore creaGiocatore(String nome) {
		
		try {
			return  Giocatore.getInstance(nome);
		} catch (GiocatoreException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Personaggio creaPersonaggio(List<String> testo) {

		
		return null;
	}
	
	
	private static Link creaLink(List<String> testo) {
		
		
		return null;
	}
}
