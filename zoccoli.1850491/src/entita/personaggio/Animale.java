package entita.personaggio;

import entita.Entita;
import utilita.interfaccie.tag.Inventario;

public abstract class Animale extends Entita implements Inventario{

	public Animale(String nome) {
		super(nome);
	}

}
