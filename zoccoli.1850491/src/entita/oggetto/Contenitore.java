package entita.oggetto;

import java.util.HashSet;
import java.util.Set;

import utilita.interfaccie.tag.Inventario;
import utilita.interfaccie.tag.Observer;

public abstract class Contenitore extends Oggetto{
	protected Set<Inventario> inventario;
	
	public Contenitore(String nome) {
		super(nome);
		inventario = new HashSet<>();
	}
	
}
