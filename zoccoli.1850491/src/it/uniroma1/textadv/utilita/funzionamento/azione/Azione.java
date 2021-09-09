package it.uniroma1.textadv.utilita.funzionamento.azione;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Aprire;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Dare;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Interazione;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Movimento;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Osservazione;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Prendere;
import it.uniroma1.textadv.utilita.funzionamento.azione.concreto.Usare;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.ExitException;

public abstract class Azione implements Iterable<Azione>{
	/**
	 * Lista delle classi concrete che permettono l'azione nel gioco
	 */
	public static final List<Azione> listaAzioni = List.of(	new Interazione(),
															new Aprire(),
															new Dare(),
															new Movimento(),
															new Osservazione(),
															new Prendere(),
															new Usare());
	/**
	 * Comandi esterni al gioco per poter uscire o altro
	 */
	public static final Set<String> comandoEsterno = Set.of("exit");
	
	protected static String comandoRicevuto;
	private Set<String> setComandi;

	public Azione(Set<String> setComandi) {
		this.setComandi = setComandi;
	}
	
	/**
	 * Metodo che attiva l'azione a seconda del tipo di azione da fare
	 * @param entita = {@link Entita} ricevute da analizzare
	 */
	public abstract void active(List<Entita> entita) throws AzioneException, GiocatoreException;
	
	
	public static Azione getAzione(String comando) throws ExitException {
		comandoRicevuto = comando;
		
		if(comandoEsterno.contains(comando))
			throw new ExitException();
		
		for(Azione e : listaAzioni) {
			if(e.contains(comando))
				return e;
		}
		
		return null;
	}
	
	public boolean contains(String parola) {
		return setComandi.contains(parola);
	}
	
	@Override
	public Iterator<Azione> iterator(){
		return listaAzioni.iterator();
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
