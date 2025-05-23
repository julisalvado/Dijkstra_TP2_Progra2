package prin;

import implementaciones.ConjuntoAR;
import implementaciones.Dijkstra;
import implementaciones.GrafoMA;
import interfaces.ConjuntoTDA;
import interfaces.GrafoTDA;

public class prin {
    public static void main(String[] args) {
      
        GrafoTDA grafo = new GrafoMA();
        grafo.inicializarGrafo(); 

        for (int i = 1; i <= 5; i++) {
            grafo.agregarVertice(i);
        }

        grafo.agregarArista(1, 2, 10);
        grafo.agregarArista(1, 3, 5);
        grafo.agregarArista(2, 3, 2);
        grafo.agregarArista(3, 2, 3);
        grafo.agregarArista(2, 4, 1);
        grafo.agregarArista(3, 4, 9);
        grafo.agregarArista(3, 5, 2);
        grafo.agregarArista(5, 4, 6);
        grafo.agregarArista(4, 5, 4);

        int origen = 1;

        Dijkstra.GrafosResultado resultado = Dijkstra.resolver(grafo, origen);

        // Mostrar grafo de costos mínimos desde origen
        System.out.println("Grafo de costos mínimos desde nodo " + origen + ":");
        imprimirGrafo(resultado.grafoCostos);

        // Mostrar grafo de caminos mínimos
        System.out.println("Grafo de caminos mínimos:");
        imprimirGrafo(resultado.grafoCaminos);
    }

    public static void imprimirGrafo(GrafoTDA grafo) {
    	ConjuntoTDA vertices = grafo.vertices();
    	ConjuntoTDA copiaVertices = new ConjuntoAR();
    	copiaVertices.inicializarConjunto();

    	while (!vertices.conjuntoVacio()) {
        	int u = vertices.elegir();
        	copiaVertices.agregar(u);
        	vertices.sacar(u);

        	ConjuntoTDA vertices2 = grafo.vertices();
        	ConjuntoTDA copiaVertices2 = new ConjuntoAR();
        	copiaVertices2.inicializarConjunto();

        	while (!vertices2.conjuntoVacio()) {
            	int v = vertices2.elegir();
            	copiaVertices2.agregar(v);
            	vertices2.sacar(v);

            	if (grafo.existeArista(u, v)) {
                	int peso = grafo.pesoArista(u, v);
                	System.out.println("Arco de " + u + " a " + v + " con peso " + peso);
            	}
        	}

        	while (!copiaVertices2.conjuntoVacio()) {
            	int v = copiaVertices2.elegir();
            	vertices2.agregar(v);
            	copiaVertices2.sacar(v);
        	}
    	}

    	while (!copiaVertices.conjuntoVacio()) {
        	int u = copiaVertices.elegir();
        	vertices.agregar(u);
        	copiaVertices.sacar(u);
    	}
	}

}
