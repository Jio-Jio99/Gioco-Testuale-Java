package entita.oggetto;

import java.util.Set;

public class Contenitore extends Oggetto{
	private Set<Oggetto> inventario;
	
	public Contenitore(String nome) {
		super(nome);
	}

}
