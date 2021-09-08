package utilita.azione;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import entita.Entita;
import entita.Mondo;
import entita.link.Link;
import entita.link.concreto.Libero;
import entita.personaggio.concreto.Giocatore;
import entita.stanza.Stanza;
import it.uniroma1.textadv.Gioco;
import utilita.azione.eccezioni.AzioneException;
import utilita.azione.eccezioni.concreto.ComandoNonRiconosciutoException;
import utilita.azione.eccezioni.concreto.OggettoNonInStanzaException;
import utilita.creazione.AnalizzaFile;
import utilita.creazione.eccezioni.GiocatoreException;
import utilita.creazione.eccezioni.concreto.PuntoCardinaleException;
import utilita.enumerazioni.PuntoCardinale;
import utilita.interfaccie.FilesMethod;

public abstract class AnalizzaComando {
	
	public static void main(String[] args) throws Exception {
		Mondo m = AnalizzaFile.analizzaLista(FilesMethod.lettura(Paths.get("resourse", "minizak.game")));
		String s = "";
		
		while(true) {
			s = Gioco.input();
			if(s.equals("fine")) break;
			analizzaComando(m, s);
		}
	}
	
	public static void analizzaComando(Mondo mondo, String comandoString) throws AzioneException, GiocatoreException {
		List<String> comando = stringInList(comandoString);
		
		Stanza stanza = Giocatore.getInstance().getPosizione();
		
		TipoAzione azione = null;
		
		for(String s : comando) {
			azione = TipoAzione.getTipoAzione(s);
			if(azione != null) break;
		}
		
		if(azione == null)
			throw new ComandoNonRiconosciutoException();
		
		List<Entita> entitaInComando = new ArrayList<>();
		List<String> supporto;
		
		for(Entita e : mondo.getEntita()) {
			supporto = stringInList(e.getNome());
			if(Collections.indexOfSubList(comando, supporto) != -1)
				entitaInComando.add(e);
		}
		
		
		
		Entita[] disponibili = entitaInComando.stream().filter(x -> stanza.getEntita().contains(x)).toArray(Entita[]::new);
		
		if(!entitaInComando.isEmpty())
			System.out.println(Arrays.toString(disponibili) + entitaInComando.get(0).getClass());
		
		if(azione == TipoAzione.VAI) {
			PuntoCardinale p = null;
			for(String s : comando) {
				try {
					p = PuntoCardinale.getDirezione(s);
				} catch (PuntoCardinaleException e1) {continue;}
			}
				
			disponibili = new Entita[1];
			if(p != null) {
				disponibili[0] = stanza.getAccessi().get(p);
			}
			else {
				disponibili[0] = null;
			}
		}	
		
		if(disponibili.length != entitaInComando.size())
			throw new OggettoNonInStanzaException();

		azione.active(disponibili);
	}
	
	/**
	 * Metodo che trasforma una stringa nella lista di parole contenute
	 * @param stringa 
	 * @return
	 */
	private static List<String> stringInList(String stringa){
		return Arrays.asList(stringa.split(" ")).stream().map(String::strip).filter(Predicate.not(String::isBlank)).collect(Collectors.toList());
	}
}
