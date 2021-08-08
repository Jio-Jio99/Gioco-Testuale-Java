package utilita.interfaccie;

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
//	final Map<String, Function<List<String>, Entita>> dizionario_funzioni = Stream.of(new Object[][]
//			{
//		
//				{"room", x -> creaStanza(x)},
//				{"objects", x -> creaOggetto(x)},
//				{"links", x -> creaLink(x)},
//				{"player", x -> creaGiocatore(x.get(0))},
//				{"characters", x -> creaPersonaggio(x)}
//	
//			}).collect(Collectors.toMap(k -> (String) k[0], v -> (Function<List<String>, Entita>) v[1]));
	

	public static void analizzaLista(List<String> lista) {
		Map<String, Function<List<String>, Entita>> dizionario_creazione = new HashMap<>();
					dizionario_creazione.put("room", x -> creaStanza(x));
					dizionario_creazione.put("objects", x -> creaOggetto(x));
					dizionario_creazione.put("links", x -> creaLink(x));
					dizionario_creazione.put("player", x -> creaGiocatore(x.get(0)));
					dizionario_creazione.put("characters", x -> creaPersonaggio(x));
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
