package it.uniroma1.textadv.entita.oggetto.concreto;

import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.entita.oggetto.Chiavistello;

/**
 * Classe Tronchesi, Chiavistello
 * @author gioele
 *
 */
public class Tronchesi extends Chiavistello implements Inventario{

	public Tronchesi(String nome) {
		super(nome);
	}
	
	
	@Override
	public String guarda() {
		return "È una" + getNome().toLowerCase();
	}
}
