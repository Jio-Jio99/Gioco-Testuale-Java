package it.uniroma1.textadv.entita.personaggio.concreto;

import java.util.Scanner;

import it.uniroma1.textadv.Gioco;
import it.uniroma1.textadv.entita.personaggio.Umano;

public class Venditore extends Umano{

	public Venditore(String nome) {
		super(nome);
	}
	
	@Override
	public void interazione() {
		super.interazione();
		System.out.println("«Come sta? »");
		
		Gioco.input();
		
		System.out.println("«AHAHAH Farò finta di aver capito... purtoppo il nostro programmatore è un po' pigro e non è che\n"
				+ "ci abbia detto molto su come parlare! Scusami ancora, ma volevo essere gentile! Spero comunque tu stia bene!»");
	}

}
