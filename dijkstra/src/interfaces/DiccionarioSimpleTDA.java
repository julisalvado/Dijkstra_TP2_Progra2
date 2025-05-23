package interfaces;

public interface DiccionarioSimpleTDA {
	void inicializarDiccionario();			// Sin precondiciones.
	void agregar(int clave, int x);			// Diccionario inicializado.
	void eliminar(int clave);				// Diccionario inicializado.
	int recuperar(int clave);				// Diccionario inicializado y clave existente.
	ConjuntoTDA claves();					// Diccionario inicializado.
}
