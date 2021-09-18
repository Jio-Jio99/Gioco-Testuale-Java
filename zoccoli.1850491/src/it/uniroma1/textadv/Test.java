package it.uniroma1.textadv;

public class Test{
	/**
	 * Directory del file del mondo di default
	 */
	public static final String PATH_MONDO = "minizak.game";
	
	/**
	 * Directory del file dello script di default
	 */
	public static final String PATH_SCRIPT = "minizak.ff";

	public static void main(String[] args) throws Exception
	{
		Gioco g = new Gioco();
		Mondo m = Mondo.fromFile(PATH_MONDO);
		
		System.out.println("-Vuoi giocare in prima persona? [digita 'si' per giocare in prima persona, altrimenti si attiverà lo script di default] ");
		
		if(Gioco.input().strip().toLowerCase().contains("si")) 
			g.play(m);
		else
			g.play(m, PATH_SCRIPT);
	}

}