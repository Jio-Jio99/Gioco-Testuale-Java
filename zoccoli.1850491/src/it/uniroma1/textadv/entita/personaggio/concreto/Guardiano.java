package it.uniroma1.textadv.entita.personaggio.concreto;

import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.entita.personaggio.Umano;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

/**
 * Classe che rappresenta la guardia dell'oggetto finale
 * @author gioele
 *
 */
public class Guardiano extends Umano {
	private boolean distratto;
	
	public Guardiano(String nome) {
		super(nome);
	}
	
	@Override
	public void interazione() {
		System.out.println("Ti guarda dall'alto in basso...\n\t\t«TU... NON PUOI... PASSARE!»");
	}
	
	@Override
	public Inventario dai(String nomeInventario) throws AzioneException {
		if(distratto)
			return getOggetto(nomeInventario).orElseThrow(() -> new AzioneException("«Non ce l'ho questo... e anche se lo avessi non te lo darei!»"));
		
		throw new AzioneException("Guardiano: «HEY TU!! SE CI RIPROVI...!! TI AMMAZZO!»");
	}
	
	/**
	 * Override del metodo prendi, se il guardiano riceve il gatto, si distrae e puoi prendere ciò che custodisce
	 */
	@Override 
	public void prendi(Inventario o) {
		super.prendi(o);
		
		if(o instanceof Gatto) {
			distratto = true;
			System.out.println("«UUH che bel gattino! Ma dover eri?!»");
		}
	}
}
