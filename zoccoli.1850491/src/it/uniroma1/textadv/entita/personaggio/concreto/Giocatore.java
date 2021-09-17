package it.uniroma1.textadv.entita.personaggio.concreto;

import java.util.HashSet;
import java.util.stream.Collectors;

import it.uniroma1.textadv.entita.Entita;
import it.uniroma1.textadv.entita.StatoGioco;
import it.uniroma1.textadv.entita.interfaccia.Description;
import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.entita.link.Link;
import it.uniroma1.textadv.entita.link.MezzoDiTrasporto;
import it.uniroma1.textadv.entita.oggetto.concreto.Tesoro;
import it.uniroma1.textadv.entita.personaggio.Animale;
import it.uniroma1.textadv.entita.personaggio.Personaggio;
import it.uniroma1.textadv.utilita.creazione.eccezioni.GiocatoreException;
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.GiocatoreNomeDiversoException;
import it.uniroma1.textadv.utilita.creazione.eccezioni.concreto.GiocatoreNonInstanziatoException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.LinkChiusoException;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.concreto.OggettoNonInInventarioException;

/**
 * Classe del Giocatore, che rappresenta l'utente ed esegue le azioni richieste 
 * @author gioele
 *
 */
public class Giocatore extends Personaggio{
	private static Giocatore instanza;
	private static StatoGioco statoGioco;
	
	private Giocatore(String nome) {
		super(nome);
		setInventario(new HashSet<>());
		statoGioco = StatoGioco.IN_GIOCO;
	}

	//METODI GET
	/**
	 * Metodo che restituisce l'entita presente nell'inventario del Giocatore
	 * @param nome
	 * @return
	 */
	public boolean getEntita(String nome) {
		return inventario.get(nome) == null ? false : true;
	}
	
	/**
	 * Metodo che ritorna se il Giocatore ha trovato il tesoro e quindi ha vinto la partita o meno
	 * @return
	 */
	public StatoGioco getStato() {
		return statoGioco;
	}
	
	/**
	 * Metodo che setta lo stato del giocatore
	 */
	public void setStato(StatoGioco stato) {
		statoGioco = stato;
	}
	
	//METODI PER LE AZIONI
	//GUARDA
	/**
	 * Metodo che descrive cosa si sta guardando
	 * @param e = entita in osservazione
	 */
	public void guarda(Description e) {
		System.out.println(e.guarda());	
	}
	
	/**
	 * Metodo per vedere cosa si ha nell'inventario
	 */
	public void inventario() {
		String stringa = inventario.isEmpty() ? "Zaino vuoto!" : "Nello zaino: " + inventario.keySet().stream().collect(Collectors.joining(",\n\t\t"));
		System.out.println(stringa);
	}
	
	
	//PRENDI
	/**
	 * Metodo che aggiunge un oggetto all'Inventario se non è già presente
	 * @param o
	 */
	@Override
	public void prendi(Inventario o) {
		if(inventario.putIfAbsent(((Entita)o).getNome(), o) == null) {
			System.out.println(o instanceof Animale ? o + "... ti segue!" : o + " aggiunto all'inventario!");
			if(o instanceof Tesoro)
				setStato(StatoGioco.VINTO);
		}
		else 
			System.out.println("Ehm...già lo hai... perché rimetterlo dentro?");
	}
	
	
	//MOVIMENTO
	/**
	 * Metodo che sposta il giocatore da un luogo ad un altro tramite i link, se essi sono aperti
	 * @param accesso
	 * @throws LinkChiusoException
	 */
	public void vai(Link accesso) throws LinkChiusoException {
		if(!accesso.passaggio(this)) 
			throw new LinkChiusoException();
		
		if(accesso instanceof MezzoDiTrasporto)
			System.out.println("Bruuum!");
		else
			System.out.println("Passato!");
	}
	
	
	//INTERAZIONE CON ALTRI PERSONAGGI
	/**
	 * Metodo per dare ad un Personaggio specifico l'oggetto richiesto
	 * @param oggetto
	 * @param p
	 * @throws OggettoNonInInventarioException
	 */
	public void dai(String oggetto, Personaggio p) throws OggettoNonInInventarioException {
		Inventario in = getOggetto(oggetto).orElseThrow(OggettoNonInInventarioException::new);
		p.prendi(in);
	}
	
	public void dai(Inventario oggetto, Personaggio p) throws OggettoNonInInventarioException {
		dai(((Entita)oggetto).getNome(), p);
	}
	
	/**
	 * Interazione con se stesso... beh.. non fa nulla se non le domande della vita volendo parlare con se stesso
	 */
	@Override
	public void interazione() {
		System.out.println("Ehm..sogno o son desto? Son sempre io? Perché esisto? E altre domande di cui non sapremo mai la verita'");
	}
	
	//METODI PER L'INSTANZA
	/**
	 * Metodo che instanzia il Giocatore, se non instanziato precedentemnte, altrmenti restituisce la stessa instanza
	 * @param nome = nome che si vuole dare al Giocatore
	 * @return {@link Giocatore}
	 * @throws GiocatoreException
	 */
	public static Giocatore getInstance(String nome) throws GiocatoreException {
		if(instanza == null)
			instanza = new Giocatore(nome);
		
		else if(!instanza.NOME.equals(nome))
			throw new GiocatoreNomeDiversoException();
		
		return instanza;
	}
	
	/**
	 * Metodo che ritorna il Giocatore già instanziato
	 * @return
	 * @throws GiocatoreException
	 */
	public static Giocatore getInstance() throws GiocatoreException {
		if(instanza == null) 
			throw new GiocatoreNonInstanziatoException();
		
		return instanza;
	}
}
