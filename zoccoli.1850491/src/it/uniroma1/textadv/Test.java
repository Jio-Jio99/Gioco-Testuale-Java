package it.uniroma1.textadv;

import entita.Mondo;

public class Test{
	public static void main(String[] args) throws Exception{
		Gioco g = new Gioco();
		Mondo m = Mondo.fromFile("minizak.game");
		g.play(m);
	}
}