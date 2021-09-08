package utilita.azione;

import java.util.Set;

import entita.Entita;
import entita.link.Link;
import entita.link.MezzoDiTrasporto;
import entita.personaggio.Personaggio;
import entita.personaggio.concreto.Giocatore;
import utilita.azione.eccezioni.AzioneException;
import utilita.azione.eccezioni.concreto.AccessoNonDisponibileException;
import utilita.azione.eccezioni.concreto.ComandoScrittoNonCorrettamenteException;
import utilita.azione.eccezioni.concreto.IncoerenzaEntitaAzioneException;
import utilita.azione.eccezioni.concreto.OggettoNonInInventarioException;
import utilita.azione.interfaccia.Description;
import utilita.creazione.eccezioni.GiocatoreException;
import utilita.interfaccie.Inventario;

public enum TipoAzione {
	INVENTARIO(Set.of("inventario")){
		public void active(Entita... entita) throws GiocatoreException, AzioneException {
			if(entita.length != 0) throw new ComandoScrittoNonCorrettamenteException();
			
			Giocatore.getInstance().inventario();
		}
	},
	PRENDI(Set.of("prendi")){
		public void active(Entita... entita) throws AzioneException, GiocatoreException {
			if(entita[0] instanceof MezzoDiTrasporto) {
				if(entita.length == 1)
					Giocatore.getInstance().prendi((MezzoDiTrasporto) entita[0]);
				else
					throw new IncoerenzaEntitaAzioneException();
			}

			for(Entita e : entita)
				Giocatore.getInstance().prendi((Inventario) e);
		}
	},
	USA(Set.of("usa", "rompi")){
		public void active(Entita... entita) {

		
		}
	},
	APRI(Set.of("apri")){
		public void active(Entita... entita) {
			
		
		}
	},
	VAI(Set.of("vai", "entra")){
		public void active(Entita... entita) throws AzioneException, GiocatoreException {
			if(entita.length == 0 || entita[0] == null)
				throw new AccessoNonDisponibileException();
			
			Giocatore.getInstance().vai((Link) entita[0]);
		}
	},
	GUARDA(Set.of("guarda", "osserva")){
		public void active(Entita... entita) throws GiocatoreException {
			if(entita.length == 1) {
				Description d = (Description) entita[0];
				Giocatore.getInstance().guarda(d);
			}
			else if(entita.length == 0 || entita == null)
				Giocatore.getInstance().guarda();
			else
				System.out.println("Non puoi guardare più cose assieme... senza offesa per gli strabici");
		}
	},
	INTERAZIONE(Set.of("parla", "accarezza")){
		public void active(Entita... entita) throws AzioneException, GiocatoreException {
			((Personaggio) entita[0]).interazione();
		}
	},
	DAI(Set.of("dai")){
		public void active(Entita... entita) throws OggettoNonInInventarioException, GiocatoreException {
			Giocatore.getInstance().dai((Inventario) entita[1], (Personaggio) entita[0]);
		}
	};
	
	private Set<String> azione;
	
	private TipoAzione(Set<String> azione) {
		this.azione = azione;
	}
	
	public static TipoAzione getTipoAzione(String azioneString){
		for(TipoAzione e : TipoAzione.values()) 
			if(e.azione.contains(azioneString)) 
				return e;
		
		return null;
	}
	
	public abstract void active(Entita... entita) throws AzioneException, GiocatoreException;
}
