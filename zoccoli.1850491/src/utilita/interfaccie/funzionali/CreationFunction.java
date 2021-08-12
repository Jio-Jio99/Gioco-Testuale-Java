package utilita.interfaccie.funzionali;

import java.util.List;

import entita.Entita;
import utilita.eccezioni.MondoFileException;

@FunctionalInterface
public interface CreationFunction {
	
	Entita apply(List<String> pattern) throws MondoFileException;

}
