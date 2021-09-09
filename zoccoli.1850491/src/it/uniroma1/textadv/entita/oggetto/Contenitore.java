package it.uniroma1.textadv.entita.oggetto;


import it.uniroma1.textadv.entita.interfaccia.Apribile;
import it.uniroma1.textadv.utilita.creazione.AnalizzaFile;
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.EntitaException;
import it.uniroma1.textadv.utilita.creazione.interfaccia.Observer;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.ChiusoException;

public abstract class Contenitore extends Oggetto implements Observer, Apribile{
	protected Oggetto inventario;
	private String inventarioString;
	protected boolean aperto;
	protected boolean chiusoConChiave;
	
	public Contenitore(String nome) {
		super(nome);
		chiusoConChiave = true;
	}
	
	public void setInventario(String inventario) {
		inventarioString = inventario;
	}
	
	public Oggetto getOggetto() throws ChiusoException {
		if(aperto)
			return inventario;
		throw new ChiusoException(this);
	}
	
	//METODI PER INTERAGIRE
	@Override
	public String guarda() {
		return aperto ? "Ecco cosa trovi dentro: " + (inventario == null ? "ops... nulla" : inventario) : "È un " + getNome() + " chiuso/a";
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
		
		if(!chiusoConChiave) {
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
