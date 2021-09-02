package utilita.interfaccie.funzionali;

import java.util.List;
import java.util.Set;

import entita.Entita;
import utilita.eccezioni.MondoFileException;

/**
 * Interfaccia funzionale per i metodi della creazione dell'Entita 
 * @author gioele
 */
@FunctionalInterface
public interface CreationFunction {
	Set<? extends Entita> apply(List<String> pattern) throws MondoFileException;
}
