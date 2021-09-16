package it.uniroma1.textadv.utilita.funzionamento.azione.concreto;

import java.util.Set;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.interfaccia.Utilizzato;
import it.uniroma1.textadv.entita.interfaccia.Utilizzatore;
import it.uniroma1.textadv.entita.link.Link;
import it.uniroma1.textadv.entita.oggetto.Chiavistello;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.azione.Azione;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

/**
 * Classe che abina due entita: {@link Utilizzatore} e {@link Utilizzato} per farli interagire fra loro 
 * @author gioele
 *
 */
public class Usare extends Azione{
	public static final Set<String> COMANDI = Set.of("usa", "rompi");
	public static final String 	USA = "usa",
								SU = "su";
	public Usare() {
		super(COMANDI);
	}

	/**
	 * Metodo che dato un Utilizzatore applica il suo effetto sull'Utilizzato. <p>
	 * Ci sono 2 casi particolari: </p>
	 * - se l'Utilizzatore è un {@link Chiavistello}, allora applica l'azione dell'aprire <p>
	 * - se è un {@link Link}, quello del movimento
	 */
	@Override
	public void active(Entita entita1, Entita... entita2) throws AzioneException, GiocatoreException {
		if(entita1 instanceof Chiavistello && entita2.length > 0) {
			new Aprire().active(entita2[0], entita1);
			return;
		}
		else if(entita1 instanceof Link) {
			new Movimento().active(entita1, entita2);
			return;
		}
		
		if(entita1 instanceof Utilizzato && entita2.length != 2) 
			((Utilizzato) entita1).effetto(null);
		else {
			Utilizzatore u = (Utilizzatore) entita1;
			u.usa((Utilizzato) entita2[0]);
		}
	}
}
