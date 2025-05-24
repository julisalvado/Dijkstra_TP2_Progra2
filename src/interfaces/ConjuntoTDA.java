package interfaces;

public interface ConjuntoTDA {
	void inicializarConjunto();		// Sin precondiciones.
	void agregar(int x);			// Conjunto inicializado.
	void sacar(int x);				// Conjunto inicializado.
	int elegir();					// Conjunto inicializado y no vac�o.
	boolean pertenece(int x);		// Conjunto inicializado.
	boolean conjuntoVacio();		// Conjunto inicializado.
}

