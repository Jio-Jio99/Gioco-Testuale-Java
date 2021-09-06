package entita.personaggio.concreto;

import java.util.HashSet;

import entita.Entita;
import entita.oggetto.Oggetto;
import entita.personaggio.Personaggio;
import utilita.azione.interfaccia.Description;
import utilita.creazione.eccezioni.GiocatoreException;
import utilita.creazione.eccezioni.concreto.GiocatoreNomeDiversoException;
import utilita.creazione.eccezioni.concreto.GiocatoreNonInstanziatoException;

public class Giocatore extends Personaggio{
	private static Giocatore instanza;
	
	private Giocatore(String nome) {
		super(nome);
		setInventario(new HashSet<>());
	}
	
	
	//METODI PER LE AZIONI
	public String osserva(Entita e) {
		String descr = e.getNome() + " ";
		
		if(e instanceof Description)
			descr += ((Description) e).guarda();
		
		return descr;
	}
	
	
	public void prendi(Oggetto o) {
		inventario.put(o.toString(), o);
	}
	
	
	
	
	
	
	
	
	/**
	 * Metodo che instanzia il Giocatore, se non instanziato precedentemnte, altrmenti restituisce la stessa instanza
	 * @param nome = nome che si vuole dare al Giocatore
	 * @return {@link Giocatore}
	 * @throws GiocatoreException
	 */
	public static Giocatore getInstance(String nome) throws GiocatoreException {
		if(instanza == null)
			instanza = new Giocatore(nome);
		
		else if(!instanza.NOME.equals(nome))
			throw new GiocatoreNomeDiversoException();
		
		return instanza;
	}
	
	/**
	 * Metodo che ritorna il Giocatore già instanziato
	 * @return
	 * @throws GiocatoreException
	 */
	public static Giocatore getInstance() throws GiocatoreException {
		if(instanza == null) 
			throw new GiocatoreNonInstanziatoException();
		
		return instanza;
	}
}
