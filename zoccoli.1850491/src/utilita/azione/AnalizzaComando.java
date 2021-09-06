package utilita.azione;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import entita.personaggio.concreto.Giocatore;
import entita.stanza.Stanza;
import utilita.creazione.eccezioni.GiocatoreException;

public abstract class AnalizzaComando {
	private static Stanza stanza;
	private static Set<String> oggetti;
	private static Set<String> personaggi;
	
	public static void analizzaComando(String comandoString) {
		List<String> comando = stringaALista(comandoString);
		
		try {
			Stanza stanzaSupporto = Giocatore.getInstance().getPosizione();
			if(!stanza.equals(stanzaSupporto)) 
				stanza = stanzaSupporto;
			
		} catch (GiocatoreException e) {
			e.printStackTrace();
		}
		
		
	
//		Collections.indexOfSubList(comando, comando);
	}
	
	
	
	
	//METODI DI SUPPORTO
	private static List<String> stringaALista(String stringa){
		return Arrays.asList(stringa.split(" ")).stream().map(x -> x.strip()).filter(Predicate.not(String::isBlank)).collect(Collectors.toList());
	}
}
