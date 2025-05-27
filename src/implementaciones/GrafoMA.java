package implementaciones;

import interfaces.ConjuntoTDA;
import interfaces.GrafoTDA;
import java.util.HashMap;

public class GrafoMA implements GrafoTDA {
    static int max = 100;		// Cantidad maxima de nodos del grafo.
    int[][] mAdy;				// Matriz de adyacencia.
    int cantidadVertices;		// Cantidad actual de vertices.
    int[] etiquetas;			// Arreglo para relacionar vertices con indices. 
    HashMap<Integer, Integer> etiquetaAIndice; //mapeo etiqueta -> índice

    @Override
    public void inicializarGrafo() {		// O(1);
        mAdy = new int[max][max];
        etiquetas = new int[max];
        etiquetaAIndice = new HashMap<>();
        cantidadVertices = 0;
    }

    @Override
    public void agregarVertice(int x) {					//O(n).
        etiquetas[cantidadVertices] = x;
        etiquetaAIndice.put(x, cantidadVertices); 
        for (int i = 0; i <= cantidadVertices; i++) {
            mAdy[i][cantidadVertices] = 0;
            mAdy[cantidadVertices][i] = 0;
        }
        cantidadVertices++;
    }

    @Override
    public void eliminarVertice(int x) {				//O(n).
        int inx = vertice2indice(x);
        etiquetaAIndice.remove(x);
        if (inx != cantidadVertices - 1) {
            etiquetaAIndice.put(etiquetas[cantidadVertices - 1], inx);
        }
        etiquetas[inx] = etiquetas[cantidadVertices - 1];
        for (int j = 0; j < cantidadVertices; j++) mAdy[inx][j] = mAdy[cantidadVertices-1][j];
        for (int j = 0; j < cantidadVertices; j++) mAdy[j][inx] = mAdy[j][cantidadVertices-1];
        cantidadVertices--;
    }

    @Override
    public void agregarArista(int x, int y, int w) {	//O(1).
        int origen = vertice2indice(x);
        int destino = vertice2indice(y);
        mAdy[origen][destino] = w;
    }

    @Override
    public void eliminarArista(int x, int y) {			//O(1).
        int origen = vertice2indice(x);
        int destino = vertice2indice(y);
        mAdy[origen][destino] = 0;
    }

    @Override
    public int pesoArista(int x, int y) {				//O(1).
        int origen = vertice2indice(x);
        int destino = vertice2indice(y);
        return mAdy[origen][destino];
    }

    @Override
    public boolean existeArista(int x, int y) {			//O(1).
        int origen = vertice2indice(x);
        int destino = vertice2indice(y);
        return (mAdy[origen][destino] != 0);
    }

    @Override
    public ConjuntoTDA vertices() {						//O(n).
        ConjuntoTDA vertices = new ConjuntoAR();
        vertices.inicializarConjunto();
        for (int i = 0; i < cantidadVertices; i++) vertices.agregar(etiquetas[i]);
        return vertices;
    }

    private int vertice2indice(int vertice) { 		// O(1)
        Integer idx = etiquetaAIndice.get(vertice);
        if (idx == null) throw new RuntimeException("Vertice no encontrado: " + vertice);
        return idx;
    }

	@Override
	public ConjuntoTDA vecinos(int x) {	 // O(n)
		int origen = vertice2indice(x);
		ConjuntoTDA vecinos = new ConjuntoAR();
		vecinos.inicializarConjunto();
		for (int i = 0; i < cantidadVertices; i++) {
			if (mAdy[origen][i] != 0) {
				vecinos.agregar(etiquetas[i]);
			}
		}
		return vecinos;
	}
}
