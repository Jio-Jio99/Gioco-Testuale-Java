package entita.oggetto.concreto;

import entita.oggetto.Oggetto;
import utilita.interfaccie.Inventario;

public class Soldi extends Oggetto implements Inventario {

	public Soldi(String nome) {
		super(nome);
	}
	
	@Override
	public String guarda() {
		return "Sono dei soldi!! Uaoh ricco!";
	}
}
