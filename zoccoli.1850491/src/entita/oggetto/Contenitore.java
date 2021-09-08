package entita.oggetto;


import utilita.creazione.AnalizzaFile;
import utilita.creazione.eccezioni.concreto.EntitaException;
import utilita.creazione.interfaccia.Observer;
import utilita.interfaccie.Inventario;

public abstract class Contenitore extends Oggetto implements Observer{
	protected Inventario inventario;
	private String inventarioString;
	private boolean aperto;
	
	public Contenitore(String nome) {
		super(nome);
	}
	
	public void setInventario(String inventario) {
		inventarioString = inventario;
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
			inventario = (Inventario) AnalizzaFile.convertitore(inventarioString);
	}
}
