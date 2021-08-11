package utilita.interfaccie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import entita.Entita;
import entita.link.Link;
import entita.oggetto.Oggetto;
import entita.personaggio.Personaggio;
import entita.personaggio.concreto.Giocatore;
import entita.stanza.Stanza;
import utilita.eccezioni.GiocatoreException;

public interface AnalizzaFile {
	String PATH_OGGETTI = "";
	String PATH_PERSONAGGIO = "";
	String PATH_LINK = "";
	String PATH_STANZA = "";
	

	Map<String, Function<List<String>, Entita>> dizionario_funzioni =
												Map.of(	"room", AnalizzaFile::creaStanza,
														"objects", AnalizzaFile::creaOggetto,
														"links", AnalizzaFile::creaLink ,
														"player", x -> creaGiocatore(x.get(0)),
														"characters", AnalizzaFile::creaPersonaggio);
			
	public static void analizzaLista(List<String> lista) {

	}
	
	private static Oggetto creaOggetto(List<String> testo) {

		
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
