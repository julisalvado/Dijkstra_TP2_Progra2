package prin;

import implementaciones.Dijkstra;
import implementaciones.GrafoMA;
import interfaces.ConjuntoTDA;
import interfaces.GrafoTDA;

public class App {
    public static int contarVertices(GrafoTDA g) {
		int cant = 0;
		int x;
		ConjuntoTDA c = g.vertices();
		while(!c.conjuntoVacio()) {
			x = c.elegir();
			c.sacar(x);
			cant++;
		}
		return cant;
	}

    public static void mostrarGrafo(GrafoTDA g) {
		String cadena = "";
		ConjuntoTDA v = g.vertices();
		int cantidad = contarVertices(g);
		int[] vertices = new int[cantidad];
		cadena = cadena + "\t";
		int inx = 0;
		while(!v.conjuntoVacio()) {
			int x = v.elegir();
			v.sacar(x);
			vertices[inx] = x;
			cadena = cadena + x + "\t";
			inx++;
		}
		System.out.println(cadena);
		for (int i = 0; i < cantidad; i++) {
			cadena = "";
			cadena = cadena + vertices[i] + "\t";
			for (int j = 0; j < cantidad; j++) 
				if(g.existeArista(vertices[i], vertices[j]))
					cadena = cadena + g.pesoArista(vertices[i], vertices[j]) + "\t";
				else
					cadena = cadena + "0\t";
			System.out.println(cadena);
		}
	}


    public static void main(String[] args) throws Exception {
        //inicializamos el grafo
        GrafoTDA grafo = new GrafoMA();
        grafo.inicializarGrafo();
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);
        grafo.agregarVertice(5);
        grafo.agregarVertice(6);
        grafo.agregarArista(1, 2, 2);
        grafo.agregarArista(1, 3, 1);
        grafo.agregarArista(1, 5, 5);
        grafo.agregarArista(1, 4, 7);
        grafo.agregarArista(2, 6, 2);
        grafo.agregarArista(3, 2, 2);
        grafo.agregarArista(3, 5, 2);
        grafo.agregarArista(4, 1, 3);
        grafo.agregarArista(5, 4, 2);
        grafo.agregarArista(5, 6, 2);
        grafo.agregarArista(6, 3, 3);
        
        GrafoTDA R1 = Dijkstra.obtenerGrafoCaminos(grafo, 1);
		System.out.println("Grafo de camino nuevo");
        mostrarGrafo(R1);
		GrafoTDA R2 = Dijkstra.obtenerGrafoCaminos(grafo, 1);
		System.out.println("Grafo de camino viejo");
		mostrarGrafo(R2);
    }
}
