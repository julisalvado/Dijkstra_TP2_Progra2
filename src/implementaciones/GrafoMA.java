package implementaciones;

import interfaces.ConjuntoTDA;
import interfaces.GrafoTDA;

public class GrafoMA implements GrafoTDA {
	static int max = 100;		// Cantidad maxima de nodos del grafo.
	int[][] mAdy;				// Matriz de adyacencia.
	int cantidadVertices;		// Cantidad actual de vertices.
	int[] etiquetas;			// Arreglo para relacionar vertices con indices. 

	@Override
	public void inicializarGrafo() {		// O(1);
		mAdy = new int[max][max];
		etiquetas = new int[max];
		cantidadVertices = 0;
	}

	@Override
	public void agregarVertice(int x) {					//O(n).
		etiquetas[cantidadVertices] = x;
		for (int i = 0; i <= cantidadVertices; i++) {
			mAdy[i][cantidadVertices] = 0;
			mAdy[cantidadVertices][i] = 0;
		}
		cantidadVertices++;
	}

	@Override
	public void eliminarVertice(int x) {				//O(n).
		int inx = vertice2indice(x);					 
		etiquetas[inx] = etiquetas[cantidadVertices - 1];
		for (int j = 0; j < cantidadVertices; j++) mAdy[inx][j] = mAdy[cantidadVertices-1][j];
		for (int j = 0; j < cantidadVertices; j++) mAdy[j][inx] = mAdy[j][cantidadVertices-1];
		cantidadVertices--;
	}

	@Override
	public void agregarArista(int x, int y, int w) {	//O(n).
		int origen = vertice2indice(x);
		int destino = vertice2indice(y);
		mAdy[origen][destino] = w;
	}

	@Override
	public void eliminarArista(int x, int y) {			//O(n).
		int origen = vertice2indice(x);
		int destino = vertice2indice(y);
		mAdy[origen][destino] = 0;
	}

	@Override
	public int pesoArista(int x, int y) {				//O(n).
		int origen = vertice2indice(x);
		int destino = vertice2indice(y);
		return mAdy[origen][destino];
	}

	@Override
	public boolean existeArista(int x, int y) {			//O(n).
		int origen = vertice2indice(x);
		int destino = vertice2indice(y);
		return (mAdy[origen][destino] != 0);
	}

	@Override
	public ConjuntoTDA vertices() {						//O(n^2).
		ConjuntoTDA vertices = new ConjuntoAR();
		vertices.inicializarConjunto();
		for (int i = 0; i < cantidadVertices; i++) vertices.agregar(etiquetas[i]);
		return vertices;
	}

	private int vertice2indice(int vertice) { 		// O(n)
    	int i = 0;
    	while (i < cantidadVertices && etiquetas[i] != vertice) i++;
    	if (i == cantidadVertices) throw new RuntimeException("Vertice no encontrado: " + vertice);
    	return i;
	}
}
