package it.uniroma1.textadv.entita.oggetto;


import it.uniroma1.textadv.entita.interfaccia.Apribile;
import it.uniroma1.textadv.entita.interfaccia.Datore;
import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.utilita.creazione.AnalizzaFile;
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.EntitaException;
import it.uniroma1.textadv.utilita.creazione.interfaccia.Observer;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.ChiusoException;

/**
 * Metodo che classfica tutti gli oggetti che nel loro interno possono contenerne degli altri, il funzionamento è simile a quello dei {@link Link},<p>
 * con la differenza che essi restituiranno un oggetto
 * @author gioele
 *
 */
public abstract class Contenitore extends Oggetto implements Observer, Apribile, Datore{
	protected Oggetto inventario;
	private String inventarioString;
	protected boolean aperto;
	protected boolean chiusoAChiave;
	protected boolean vuoto;
	
	public Contenitore(String nome) {
		super(nome);
		chiusoAChiave = true;
		vuoto = true;
	}
	
	/**
	 * Metodo per settare l'inventario durante la creazione dell'oggetto contenitore
	 * @param inventario
	 */
	public void setInventario(String inventario) {
		inventarioString = inventario;
		vuoto = false;
	}
	
	public boolean vuoto() {
		return vuoto;
	}
	
	/**
	 * Metodo che ritorna l'oggetto contenuto
	 * @return
	 */
	public Oggetto getOggetto() {
		return inventario;
	}
	
	/**
	 * Metodo per ricevere la stringa dell'oggetto contenuto
	 * @return
	 */
	public String getOggettoString() {
		return inventarioString;
	}
	
	/**
	 * Metodo usato per ricevere l'oggetto contenuto, dato il suo nome, rispettando i criteri per un contenitore
	 * @param nomeOggetto
	 * @return
	 * @throws AzioneException
	 */
	public Oggetto getOggetto(String nomeOggetto) throws AzioneException {
		if(aperto) {
			if(nomeOggetto.equals(inventarioString) && !vuoto) {
				vuoto = true;
				return inventario;
			}
			else if(vuoto)
				throw new AzioneException("Il contenitore è vuoto!");
			else
				throw new AzioneException("Errore! Non puoi prendere questo oggetto qui");
		}
		throw new ChiusoException(this, chiusoAChiave);
	}
	
	//METODI PER INTERAGIRE
	/**
	 * Se aperto restituisce l'oggetto contenuto in esso
	 */
	@Override
	public Inventario dai(String nomeInventario) throws AzioneException {
		if(aperto) 
			return (Inventario) getOggetto(nomeInventario);

		throw new ChiusoException(this, chiusoAChiave);
	}
	
	@Override
	public String guarda() {
		return aperto ? getClass().getSimpleName() + " aperto/a, trovi: " + (vuoto ? "ops... nulla" : inventario) : "È un " + getNome() + " chiuso/a";
	}	

	//METODI APRIBILE
	@Override
	public void apri() throws ChiusoException {
		if(aperto)
			System.out.println("È già aperto!");
		
		else if(!chiusoAChiave) {
			aperto = true;
			System.out.println(getNome() + " aperto!");
			return;
		}
		else
			throw new ChiusoException(this, chiusoAChiave);
	}
	
	@Override
	public void sblocca() {
		chiusoAChiave = false;
		aperto = true;
	}
	
	@Override 
	public void chiudi() {
		aperto = false;
	}
	
	@Override
	public void converti() throws EntitaException{
		if(inventarioString != null)
			inventario = (Oggetto) AnalizzaFile.convertitore(inventarioString);
	}
}
