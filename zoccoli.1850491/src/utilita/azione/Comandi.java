package utilita.azione;

import entita.oggetto.Oggetto;
import entita.personaggio.Personaggio;
import utilita.azione.interfaccia.Apribile;
import utilita.azione.interfaccia.Description;
import utilita.enumerazioni.PuntoCardinale;
import utilita.interfaccie.Inventario;

public interface Comandi {
	public static void apri(Apribile ap, Oggetto o) {}
	
	public static void muoviti(Personaggio p, PuntoCardinale dove) {}
	
	public static void prendi(Personaggio p, Inventario in) {}
	
	public static void usa(Personaggio p, Oggetto... oggetti) {}
	
	public static void guarda(Description d) {}

	public static void interagisci(Personaggio p1, Personaggio p2) {}
}
