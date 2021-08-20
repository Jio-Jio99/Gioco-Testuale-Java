package utilita.interfaccie.funzionali;

import java.util.List;
import java.util.Set;

import entita.Entita;
import utilita.eccezioni.MondoFileException;

@FunctionalInterface
public interface CreationFunction {
	
	Set<Entita> apply(List<String> pattern) throws MondoFileException;

}
