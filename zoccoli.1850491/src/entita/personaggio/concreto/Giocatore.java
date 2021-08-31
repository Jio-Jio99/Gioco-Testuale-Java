package entita.personaggio.concreto;

import java.util.HashSet;

import entita.personaggio.Personaggio;
import utilita.eccezioni.GiocatoreException;
import utilita.eccezioni.concreto.GiocatoreNomeDiversoException;
import utilita.eccezioni.concreto.GiocatoreNonInstanziatoException;

public class Giocatore extends Personaggio{
	private static Giocatore instanza;
	
	private Giocatore(String nome) {
		super(nome);
		setInventario(new HashSet<>());
	}
	
	
	public static Giocatore getInstance(String nome) throws GiocatoreException {
		if(instanza == null)
			instanza = new Giocatore(nome);
		
		else if(!instanza.NOME.equals(nome))
			throw new GiocatoreNomeDiversoException();
		
		return instanza;
	}
	
	public static Giocatore getInstance() throws GiocatoreException {
		if(instanza == null) 
			throw new GiocatoreNonInstanziatoException();
		
		return instanza;
	}
}
