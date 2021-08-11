package utilita.interfaccie;

import utilita.eccezioni.MondoFileException;

@FunctionalInterface
public interface Prova {
	
	String apply() throws MondoFileException;

}
