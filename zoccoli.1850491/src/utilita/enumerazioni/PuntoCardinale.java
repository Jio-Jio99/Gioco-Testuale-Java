package utilita.enumerazioni;

import utilita.creazione.eccezioni.concreto.PuntoCardinaleException;

/**
 * Enumerazione per la direzione: <p>
 * - Nord <p>
 * - Sud <p>
 * - Est <p>
 * - Ovest/West 
 * @author gioele
 *
 */
public enum PuntoCardinale {
	NORD,
	SUD,
	EST,
	OVEST;
	
	/**
	 * Metodo statico che dato in input una stringa, restituisce la coordinata giusta, altrimenti lancia l'eccezione
	 * @param direzione
	 * @return PuntoCardinale
	 * @throws PuntoCardinaleException
	 */
	public static PuntoCardinale getDirezione(String direzione) throws PuntoCardinaleException{
		direzione = direzione.toLowerCase().strip();
		
		return switch(direzione) {
			case "o" 		-> OVEST;
			case "ovest"	-> OVEST;
			case "W" 		-> OVEST;
			case "west" 	-> OVEST;
			
			case "e" 		-> EST;
			case "est" 		-> EST;
			
			case "n" 		-> NORD;
			case "nord" 	-> NORD;
			
			case "s" 		-> SUD;
			case "sud"		-> SUD;
		
			default -> throw new PuntoCardinaleException();
		};
		
	}
}
