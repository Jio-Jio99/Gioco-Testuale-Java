package it.uniroma1.textadv.entita.personaggio;

import it.uniroma1.textadv.entita.interfaccia.Inventario;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public abstract class Animale extends Personaggio implements Inventario{
	protected String verso;
	
	public Animale(String nome) {
		super(nome);
	}
	
	@Override
	public void converti() {}
	
	@Override
	public void interazione() {
		System.out.println(verso);
	}
	
	@Override
	public Inventario dai(String nomeInventario) throws AzioneException {
		throw new AzioneException("Non può darti nulla, solo coccole");
	}
}
