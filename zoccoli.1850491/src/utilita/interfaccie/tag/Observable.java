package utilita.interfaccie.tag;

public interface Observable {
	void registraObserver(Observer o);
	void cancellaObserver(Observer o);
	void notifica();
}

