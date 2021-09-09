package it.uniroma1.textadv.entita.oggetto;


import it.uniroma1.textadv.entita.interfaccia.Apribile;
import it.uniroma1.textadv.utilita.creazione.AnalizzaFile;
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.EntitaException;
import it.uniroma1.textadv.utilita.creazione.interfaccia.Observer;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.ContenitoreChiusoException;

public abstract class Contenitore extends Oggetto implements Observer, Apribile{
	protected Oggetto inventario;
	private String inventarioString;
	protected boolean aperto;
	
	public Contenitore(String nome) {
		super(nome);
	}
	
	public void setInventario(String inventario) {
		inventarioString = inventario;
	}
	
	public Oggetto getOggetto() throws ContenitoreChiusoException {
		if(aperto)
			return inventario;
		throw new ContenitoreChiusoException(getNome());
	}
	
	//METODI PER INTERAGIRE
	public boolean aperto() {
		return aperto;
	}
	
	@Override
	public String guarda() {
		return aperto ? "Ecco cosa trovi dentro: " + (inventario == null ? "ops... nulla" : inventario) : "È un " + getNome() + " chiuso/a";
	}	
	
	@Override
	public void converti() throws EntitaException{
		if(inventarioString != null)
			inventario = (Oggetto) AnalizzaFile.convertitore(inventarioString);
	}
}
