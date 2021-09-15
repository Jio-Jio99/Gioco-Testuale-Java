package it.uniroma1.textadv.entita.interfaccia;

import it.uniroma1.textadv.utilita.funzionamento.eccezioni.AzioneException;

public interface Datore {
	Inventario dai(String nomeInventario) throws AzioneException;
}
