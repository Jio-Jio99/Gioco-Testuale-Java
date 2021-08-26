package entita.oggetto;


import utilita.eccezioni.concreto.EntitaException;
import utilita.interfaccie.AnalizzaFile;
import utilita.interfaccie.tag.Inventario;
import utilita.interfaccie.tag.Observer;

public abstract class Contenitore extends Oggetto implements Observer{
	protected Inventario inventario;
	private String inventarioString;
	
	public Contenitore(String nome, String nomeOggetto) {
		super(nome);
		inventarioString = nomeOggetto;
	}
	
	@Override
	public void converti() throws EntitaException{
		inventario = (Inventario) AnalizzaFile.convertitore(inventarioString);
	}
}
