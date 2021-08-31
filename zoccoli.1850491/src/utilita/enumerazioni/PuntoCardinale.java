package utilita.enumerazioni;

import utilita.eccezioni.concreto.PuntoCardinaleException;

public enum PuntoCardinale {
	NORD,
	SUD,
	EST,
	OVEST;
	
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
