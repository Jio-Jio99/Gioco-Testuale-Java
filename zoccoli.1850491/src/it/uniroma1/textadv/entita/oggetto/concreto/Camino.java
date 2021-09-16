package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.entita.interfaccia.Utilizzato;
import it.uniroma1.textadv.entita.interfaccia.Utilizzatore;
import it.uniroma1.textadv.entita.oggetto.Contenitore;
import it.uniroma1.textadv.entita.oggetto.Oggetto;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.CaminoException;

public class Camino extends Contenitore implements Utilizzato {
	private boolean acceso;
	
	public Camino(String nome) {
		super(nome);
		acceso = true;
	}
	
	public void spegni() {
		acceso = false;
	}
	
	public void accendi() {
		aperto = true;
	}
	
	@Override
	public Inventario dai(String nomeInventario) throws AzioneException {
		if(!acceso) 
			return (Inventario) getOggetto(nomeInventario);
		throw new CaminoException();
	}
	
	@Override
	public Oggetto getOggetto(String nomeOggetto) throws AzioneException {
		if(!acceso) {
			if(nomeOggetto.equals(inventario.getNome()) && !vuoto) {
				vuoto = true;
				return inventario;
			}
			else if(vuoto)
				throw new AzioneException("Il camino non ha nulla!");
			else
				throw new AzioneException("Errore! Non puoi prendere questo oggetto qui");
		}
		throw new CaminoException();
	}
	
	@Override
	public void effetto(Utilizzatore o) throws AzioneException {
		if(o instanceof Secchio && acceso) {
			spegni();
			System.out.println("Nuvola di fumo! Camino spento!");
		}
		else if(!acceso)
			System.out.println("Il camino è già spento!");
		else
			throw new AzioneException("STAI BRUCIANDO " + o + "!!");
	}
	
	@Override
	public String guarda() {
		return "È un camino" + (aperto ? " acceso e si intravede un oggetto!" : (vuoto ? "spento... senza nulla" :" spento e vedi chiaramente l'oggetto: " + inventario));
	}
}
