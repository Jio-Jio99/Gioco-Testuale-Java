package entita.oggetto;


import utilita.creazione.AnalizzaFile;
import utilita.creazione.eccezioni.concreto.EntitaException;
import utilita.creazione.interfaccie.Inventario;
import utilita.creazione.interfaccie.Observer;

public abstract class Contenitore extends Oggetto implements Observer{
	protected Inventario inventario;
	private String inventarioString;
	
	public Contenitore(String nome) {
		super(nome);
	}
	
	public void setInventario(String inventario) {
		inventarioString = inventario;
	}
	
	@Override
	public void converti() throws EntitaException{
		if(inventarioString != null)
			inventario = (Inventario) AnalizzaFile.convertitore(inventarioString);
	}
}
