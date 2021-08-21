package utilita.interfaccie.funzionali;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import entita.Entita;
import utilita.eccezioni.MondoFileException;

@FunctionalInterface
public interface CreationFunction {
	Set<? extends Entita> apply(List<String> pattern) throws MondoFileException;
}
