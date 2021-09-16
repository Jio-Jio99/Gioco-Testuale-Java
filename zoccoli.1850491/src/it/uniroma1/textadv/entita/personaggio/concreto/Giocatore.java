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

public class Giocatore extends Personaggio{
	private static Giocatore instanza;
	
	private Giocatore(String nome) {
		super(nome);
		setInventario(new HashSet<>());
	}
	
	
	//METODI PER LE AZIONI
	//GUARDA
	/**
	 * Metodo che descrive cosa si sta guardando
	 * @param e = it.uniroma1.textadv.entita in osservazione
	 */
	public void guarda(Description e) {
		System.out.println(e.guarda());	
	}
	
	/**
	 * Metodo che descrive la stanza in cui si è
	 * @param e = it.uniroma1.textadv.entita in osservazione
	 */
	public void guarda() {
		guarda(getPosizione());
	}
	
	/**
	 * Metodo per vedere cosa si ha nell'inventario
	 */
	public void inventario() {
		String stringa = inventario.isEmpty() ? "Zaino vuoto!" : "Nello zaino: " + inventario.keySet().stream().collect(Collectors.joining(", "));
		System.out.println(stringa);
	}
	
	
	//PRENDI
	/**
	 * Metodo che aggiunge un oggetto all'Inventario se non è già presente
	 * @param o
	 */
	@Override
	public void prendi(Inventario o) {
		if(inventario.putIfAbsent(((Entita)o).getNome(), o) == null) 
			System.out.println(o instanceof Animale ? o + "... ti segue!" : o + " aggiunto all'inventario!");
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
	public void dai(String oggetto, Personaggio p) throws OggettoNonInInventarioException {
		Inventario in = getOggetto(oggetto).orElseThrow(OggettoNonInInventarioException::new);
		p.prendi(in);
	}
	
	public void dai(Inventario oggetto, Personaggio p) throws OggettoNonInInventarioException {
		dai(((Entita)oggetto).getNome(), p);
	}
	
	@Override
	public void interazione() {
		System.out.println("Ehm..sogno o son desto? Son sempre io? Perché esisto? E altre domande di cui non sapremo mai la verita'");
	}
	
	public void parla(Personaggio p) {
		p.interazione();
	}
	
	//ALTRO
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
		if(getInventario().values().stream().anyMatch(x -> x instanceof Tesoro))
			return StatoGioco.VINTO;
		
		return StatoGioco.IN_GIOCO;
	}
	
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
