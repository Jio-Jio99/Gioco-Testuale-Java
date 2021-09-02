package game;

import java.nio.file.Path;
import java.nio.file.Paths;

import entita.Mondo;

public class Main {
	
	public static void main(String[] args) throws Exception {
		Path fileName = Paths.get("resourse", "minizak.game");
		
		Gioco g = new Gioco();
		Mondo m = Mondo.fromFile(fileName);
		g.play(m);
		
		System.out.println(m);
		
	}
}
