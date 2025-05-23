package implementaciones;

import interfaces.ConjuntoTDA;
import interfaces.GrafoTDA;

public class GrafoMA implements GrafoTDA {

	//----------------------------------------------------------------------------------------------------------------------//
	// Implementaci�n est�tica de GrafoTDA. Los datos del grafo se almacenan en una matriz de adyacencia ("mAdy") y se      //
	// cuenta con un arreglo "etiquetas" que permite relacionar a los v�rtices con sus correspondientes �ndices en "mAdy".  // 
	// La implementaci�n es aceptable, con la excepci�n de este arreglo, que debe recorrerse cada vez que se quiere         //
	// trabajar con un v�rtice. Un "hashmap" ser�a una mejor opci�n.                                                        //
	//----------------------------------------------------------------------------------------------------------------------//
	
	static int max = 77;		// Cantidad m�xima de nodos del grafo.
	int[][] mAdy;				// Matriz de adyacencia.
	int cantidadVertices;		// Cantidad actual de v�rtices.
	int[] etiquetas;			// Arreglo para relacionar v�rtices con �ndices. 
	
	@Override
	public void inicializarGrafo() {		// El costo de este m�todo es O(1);
		mAdy = new int[max][max];
		etiquetas = new int[max];
		cantidadVertices = 0;							// Inicialmente el grafo est� vac�o (no tiene v�rtices).
	}

	@Override
	public void agregarVertice(int x) {					// El costo de este m�todo es O(n).
		etiquetas[cantidadVertices] = x;
		for (int i = 0; i <= cantidadVertices; i++) {	// La fila y la columna del nuevo nodo se ponen en 0.
			mAdy[i][cantidadVertices] = 0;
			mAdy[cantidadVertices][i] = 0;
		}
		cantidadVertices++;								// Y se incrementa "cantidadV�rtices".
	}

	@Override
	public void eliminarVertice(int x) {				// El costo de este m�todo es O(n).
		int inx = vertice2indice(x);					// Se busca el v�rtice por ser eliminado, que debe existir. 
		etiquetas[inx] = etiquetas[cantidadVertices - 1];
		for (int j = 0; j < cantidadVertices; j++) 		// Se sobreescriben su fila y columna con las del �ltimo v�rtice. 
			mAdy[inx][j] = mAdy[cantidadVertices-1][j];
		for (int j = 0; j < cantidadVertices; j++)
			mAdy[j][inx] = mAdy[j][cantidadVertices-1];
		cantidadVertices--;								// Y se decrementa "cantidadV�rtices".
	}

	public void agregarArista(int x, int y, int w) {	// El costo de este m�todo es O(n).
		int origen = vertice2indice(x);					// "origen" es un �ndice.
		int destino = vertice2indice(y);				// "destino" es un �ndice.
		mAdy[origen][destino] = w;
	}

	public void eliminarArista(int x, int y) {			// El costo de este m�todo es O(n).
		int origen = vertice2indice(x);					// "origen" es un �ndice.
		int destino = vertice2indice(y);				// "destino" es un �ndice.
		mAdy[origen][destino] = 0;
	}

	@Override
	public int pesoArista(int x, int y) {				// El costo de este m�todo es O(n).
		int origen = vertice2indice(x);					// "origen" es un �ndice.
		int destino = vertice2indice(y);				// "destino" es un �ndice.
		return mAdy[origen][destino];
	}

	@Override
	public boolean existeArista(int x, int y) {			// El costo de este m�todo es O(n).
		int origen = vertice2indice(x);					// "origen" es un �ndice.
		int destino = vertice2indice(y);				// "destino" es un �ndice.
		return (mAdy[origen][destino] != 0);
	}

	@Override
	public ConjuntoTDA vertices() {						// El costo de este m�todo es O(n^2).
		ConjuntoTDA vertices = new ConjuntoAR();		// Se crea el conjunto que contendr� todos los v�rtices. 
		vertices.inicializarConjunto();
		for (int i = 0; i < cantidadVertices; i++)		// Se recorre el arreglo "etiquetas" y se recolectan todas las claves. 
			vertices.agregar(etiquetas[i]);
		return vertices;
	}
	
	
	private int vertice2indice(int vertice) {
    	int i = 0;
    	while (i < cantidadVertices && etiquetas[i] != vertice) {
        	i++;
    	}
    	if (i == cantidadVertices) {
        	throw new RuntimeException("Vertice no encontrado: " + vertice);
    	}
    	return i;
	}

	
}
