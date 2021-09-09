package it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto;

import it.uniroma1.textadv.utilita.funzionamento.eccezioni.InventarioException;

public class OggettoNonInInventarioException extends InventarioException {
	public static final String ERRORE = "L'oggetto richiesto non è nell'inventario!";
	
	public OggettoNonInInventarioException() {
		super(ERRORE);
	}
}
