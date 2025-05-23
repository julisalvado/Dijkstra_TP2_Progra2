package implementaciones;

import interfaces.ConjuntoTDA;
import interfaces.DiccionarioSimpleTDA;

public class DicSimpleA implements DiccionarioSimpleTDA {

	//-------------------------------------------------------------------------------------------------------------------------//
	// Una posible implementaci�n est�tica de diccionarios simples. Cada par clave-valor est� en un objeto que los agrupa.     //
	// El conjunto de datos se almacena en un arreglo de estos objetos. Esta implementaci�n de diccionarios simples es         //
	// ineficiente. Lo mejor es representarlas como un heap. Aqu� la complejidad de agregar y eliminar un par es O(n).         //
	//-------------------------------------------------------------------------------------------------------------------------//

	class Elemento {
		int clave;
		int valor;
	}
	
	Elemento[] elementos;	// El arreglo de elementos que constituyen el diccionario. 
	int cant;				// La cantidad de elementos en el diccionario. Esta variable apunta a la primera posici�n disponible en el arreglo.
	
	public void inicializarDiccionario() {
		elementos = new Elemento[55];	// La capacidad m�xima del diccionario puede fijarse arbitrariamente.  
		cant = 0;						// Inicialmente el diccionario est� vac�o. 
	}

	public void agregar(int clave, int x) {
		int i = clave2indice(clave);			// Se busca primero la clave.
		if (i == -1) {							// Si la clave no existe, se crea un nuevo elemento.
			i = cant;
			elementos[i] = new Elemento();
			elementos[i].clave = clave;
			cant++;
		}
		elementos[i].valor = x;					// Se modifica el valor del elemento. 
	}

	public void eliminar(int clave) {
		int i = clave2indice(clave);			// Se busca primero la clave.
		if (i != -1) {							// Si no se la encuentra, no se hace nada.
			elementos[i] = elementos[cant-1];	// Si se la encuentra, se la sobreescribe con el �ltimo elemento.
			cant--;								// Y se decrementa la clave.
		}
	}

	public int recuperar(int clave) {
		int i = clave2indice(clave);
		return elementos[i].valor;
	}

	public ConjuntoTDA claves() {
		ConjuntoTDA claves = new ConjuntoAR();				// Se crea un nuevo conjunto "claves" para las claves.
		claves.inicializarConjunto();
		for (int i = 0; i < cant; i++)						// Se recolectan las claves en "claves".
			claves.agregar(elementos[i].clave);
		return claves;										// Y se devuelve "claves".
	}
	
	private int clave2indice (int clave) {		// Este m�todo privado busca el �ndice de una clave dada. Si no la encuentra, devuelve -1.
		int i = cant-1;
		while (i >= 0 && elementos[i].clave != clave)
			i--;
		return i;
	}

}