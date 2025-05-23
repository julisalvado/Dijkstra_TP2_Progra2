package interfaces;

public interface GrafoTDA {
	public void inicializarGrafo();						// Sin precondiciones.
	public void agregarVertice(int x);					// Grafo inicializado y v�rtice no existente.
	public void eliminarVertice(int x);					// Grafo inicializado y v�rtice existente.
	public void agregarArista(int x, int y, int w);		// Grafo inicializado y v�rtices existentes.
	public void eliminarArista(int x, int y);			// Grafo inicializado y arista existente.
	public int pesoArista(int x, int y);				// Grafo inicializado y arista existente.
	public boolean existeArista (int x, int y);			// Grafo inicializado y v�rtices existentes.
	public ConjuntoTDA vertices();						// Grafo inicializado.
}