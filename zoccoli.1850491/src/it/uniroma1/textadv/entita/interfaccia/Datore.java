package it.uniroma1.textadv.entita.interfaccia;

import it.uniroma1.textadv.entita.personaggio.Personaggio;
import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public interface Datore {
	Inventario dai(String nomeInventario) throws AzioneException;
	default void daiA(String nomeInventario, Personaggio p) throws AzioneException{
		p.prendi(dai(nomeInventario));
	};
}
