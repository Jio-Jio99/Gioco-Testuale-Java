package it.uniroma1.textadv.entita.oggetto;


import it.uniroma1.textadv.entita.interfaccia.Apribile;
import it.uniroma1.textadv.entita.interfaccia.Datore;
import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.utilita.creazione.AnalizzaFile;
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.EntitaException;
import it.uniroma1.textadv.utilita.creazione.interfaccia.Observer;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.ChiusoException;

public abstract class Contenitore extends Oggetto implements Observer, Apribile, Datore{
	protected Oggetto inventario;
	private String inventarioString;
	protected boolean aperto;
	protected boolean chiusoConChiave;
	private boolean vuoto;
	
	public Contenitore(String nome) {
		super(nome);
		chiusoConChiave = true;
	}
	
	public boolean vuoto(){
		return vuoto;
	}
	
	public void setInventario(String inventario) {
		inventarioString = inventario;
	}
	
	public Oggetto getOggetto() {
		return inventario;
	}
	
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
		throw new ChiusoException(this);
	}
	
	//METODI PER INTERAGIRE
	@Override
	public Inventario dai(String nomeInventario) throws AzioneException {
		if(aperto)
			return (Inventario) getOggetto(nomeInventario);

		throw new ChiusoException(this);
	}
	
	@Override
	public String guarda() {
		return aperto ? "Scrivania aperta, trovi: " + (inventario == null ? "ops... nulla" : inventario) : "È un " + getNome() + " chiuso/a";
	}	
	
	@Override
	public void converti() throws EntitaException{
		if(inventarioString != null)
			inventario = (Oggetto) AnalizzaFile.convertitore(inventarioString);
	}
	
	@Override
	public void apri() throws ChiusoException {
		if(aperto)
			System.out.println("È già aperto!");
		
		else if(!chiusoConChiave) {
			aperto = true;
			System.out.println(getNome() + " aperto!");
		}
		else 
			throw new ChiusoException(this);
	}
	
	@Override
	public void sblocca() {
		chiusoConChiave = true;
		aperto = true;
	}
}
