package it.uniroma1.textadv.utilita.creazione.interfaccia.funzionali;

import java.util.List;

import it.uniroma1.textadv.utilita.creazione.eccezioni.MondoFileException;

/**
 * Interfaccia funzionale per i metodi della creazione dell'Entita 
 * @author gioele
 */
@FunctionalInterface
public interface CreationFunction {
	void apply(List<String> pattern) throws MondoFileException;
}
