package implementaciones;

import interfaces.ConjuntoTDA;

public class ConjuntoAR implements ConjuntoTDA {

	//-------------------------------------------------------------------------------------------------------------------//
	// La implementaci�n est�tica "normal" de ConjuntoTDA. Los elementos se almacenan en un arreglo. Dado que el orden   //
	// no interesa, se agregan siempre al final. Para sacar un elemento, se lo reemplaza por el de la �ltima posici�n.   //
	// El m�todo "elegir" puede elegir cualquiera de los n�meros que pueblan el conjunto. Aqu� se elige, "por decreto",  //
	// el primero de todos. Hay otras posibilidades, como el de la implementaci�n "COnjuntoAR".                          //
	// Estas implementaciones son comparativamente ineficientes. Un "hashmap" es la mejor estructura.                    //
	//-------------------------------------------------------------------------------------------------------------------//
	
	int[] arr;		// Aqu� se guardan los n�meros del conjunto. El orden es irrelevante, por supuesto.
	int cant;		// Cantidad de elementos del conjunto.
	
	public void inicializarConjunto() {	// El costo es O(1). Todas las operaciones tienen costo constante. 
		arr = new int[20];				// Se puede usar cualquier n�mero para especificar el tama�o m�ximo del conjunto.
		cant = 0;						// Inicialmente no hay ning�n elemento. Observe que "cant" apunta a la primera posici�n libre de "arr".
	}

	public void agregar(int x) {		// El costo es O(n) por la llamada a "pertenece". 
		if (!this.pertenece(x))	{		// S�lo se agrega el elemento si no est� ya en el arreglo. 
			arr[cant] = x;
			cant++;
		}
	}

	public void sacar(int x) {				// El costo es O(n) por la llamada a "buscar". 
		int i = buscar(x);
		if (i != -1) {
			arr[i] = arr[cant-1];			// Se sobreescribe con el �ltimo elemento.
			cant--;							// Y se decrementa "cant". 
		}
	}

	public int elegir() {					// En este caso, el costo es O(1). El n�mero se elige aleatoriamente entre la poblaci�n del conjunto.
		double num = Math.random() * cant;	// Se elige un n�mero aleatorio mayor o igual a 0.0 y menor a 1.0 y se lo multiplica por "cant". 
		int inx = (int)Math.floor(num);		// Tomamos la parte entera del n�mero. 
		return arr[inx];					// Y se devuelve el valor correspondiente. 
	}

	public boolean pertenece(int x) {		// El costo es O(n) por la llamada a "buscar". 
		int i = buscar(x);
		return (i != -1);					// Si es -1, no se lo encontr�; en caso contrario, s�. 
	}

	public boolean conjuntoVacio() {		// El costo es O(1)
		return (cant == 0);					// Observe que la condici�n "cant == 0" es equivalente a "el conjunto est� vac�o."
	}
	
	private int buscar(int x) {				// El costo es O(n) por la recorrida del arreglo. El m�todo es privado.
		int i = cant-1;						// Este m�todo busca un n�mero en el conjunto y devuelve la posici�n. Si no est�, devuelve -1.
		while (i >= 0 && arr[i] != x)
			i--;
		return i;
	}
}