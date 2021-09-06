package it.uniroma1.textadv;

import java.nio.file.Paths;

import entita.Mondo;

public class Test{
	public static void main(String[] args) throws Exception{
		Gioco g = new Gioco();
		Mondo m = Mondo.fromFile(Paths.get("resourse","minizak.game"));
		g.play(m, Paths.get("resourse","minizak.ff"));
	}
}