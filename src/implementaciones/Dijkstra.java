package implementaciones;

import interfaces.ColaHeapTDA;
import interfaces.ConjuntoTDA;
import interfaces.DiccionarioSimpleTDA;
import interfaces.GrafoTDA;

public class Dijkstra {
    private static class DijkstraAux {
        public DiccionarioSimpleTDA distancias;
        public DiccionarioSimpleTDA previos;
    }

<<<<<<< HEAD
    private static DijkstraAux calcularDistanciasPrevios(GrafoTDA grafoOriginal, int origen) { //O((n+m)log n) Recorre todos los vértices una vez (n vértices).
=======
    private static DijkstraAux calcularDistanciasPrevios(GrafoTDA grafoOriginal, int origen) { //O((n+m)log n). 
>>>>>>> 9c0788197bb0b7cec7362a2185f1d67c30882d95
        DiccionarioSimpleTDA distancias = new DicSimpleA();
        distancias.inicializarDiccionario();
        DiccionarioSimpleTDA previos = new DicSimpleA();
        previos.inicializarDiccionario();
        ColaHeapTDA cola = new FilaPrioridadHeap();
        cola.inicializar(100);
        // Inicializar distancias y cola
        ConjuntoTDA nodosAux = grafoOriginal.vertices();
        while (!nodosAux.conjuntoVacio()) {    //O(n log n) recorre los n vértices una vez, cada uno realiza una inserción al heap.
            int nodo = nodosAux.elegir();
            nodosAux.sacar(nodo);
            if (nodo == origen) {
                distancias.agregar(nodo, 0);
                cola.agregarValor(nodo, 0);
            } else {
                distancias.agregar(nodo, Integer.MAX_VALUE);
                cola.agregarValor(nodo, Integer.MAX_VALUE);
            }
        }

        // Dijkstra optimizado: solo recorre vecinos reales
        while (!cola.vacio()) {       //O(n log n)
            int prioridadU = cola.obtenerPrioridad();
            int u = cola.remover();   
            if (distancias.recuperar(u) < prioridadU) continue;
            // Usar solo los vecinos reales de u
            ConjuntoTDA vecinos = grafoOriginal.vecinos(u); // <-- Método optimizado   //O(m log n), cada inserción tiene costo O(log n), recorremos m aristas por lo que el costo es O(m log n)
            while (!vecinos.conjuntoVacio()) {
                int v = vecinos.elegir();
                vecinos.sacar(v);
                int pesoUV = grafoOriginal.pesoArista(u, v);
                int nuevaDist = distancias.recuperar(u) + pesoUV;
                if (nuevaDist < distancias.recuperar(v)) {
                    distancias.agregar(v, nuevaDist);
                    previos.agregar(v, u);
                    cola.agregarValor(v, nuevaDist);
                }
            }
        }
        DijkstraAux aux = new DijkstraAux();
        aux.distancias = distancias;
        aux.previos = previos;
        return aux;
    }

    public static GrafoTDA obtenerGrafoCostos(GrafoTDA grafoOriginal, int origen) { //O((n+m)log n) Recorre todos los vértices dos veces para agregarlos y luego para agregar aristas de costos. n = cantidad de vértices, m = cantidad de aristas.
        DijkstraAux aux = calcularDistanciasPrevios(grafoOriginal, origen);
        DiccionarioSimpleTDA distancias = aux.distancias;
        GrafoTDA grafoCostos = new GrafoMA();
        grafoCostos.inicializarGrafo();
        ConjuntoTDA nodos = grafoOriginal.vertices();
        while (!nodos.conjuntoVacio()) {
            int nodo = nodos.elegir();
            nodos.sacar(nodo);
            grafoCostos.agregarVertice(nodo);
        }
        nodos = grafoOriginal.vertices();
        while (!nodos.conjuntoVacio()) {
            int nodo = nodos.elegir();
            nodos.sacar(nodo);
            if (nodo != origen && distancias.recuperar(nodo) < Integer.MAX_VALUE) {
                grafoCostos.agregarArista(origen, nodo, distancias.recuperar(nodo));
            }
        }
        return grafoCostos;
    }

    public static GrafoTDA obtenerGrafoCaminos(GrafoTDA grafoOriginal, int origen) { //O((n+m)log n) Recorre todos los vértices dos veces para agregarlos y luego para agregar aristas de caminos. n = cantidad de vértices, m = cantidad de aristas.
        DijkstraAux aux = calcularDistanciasPrevios(grafoOriginal, origen);
        DiccionarioSimpleTDA previos = aux.previos;
        GrafoTDA grafoCaminos = new GrafoMA();
        grafoCaminos.inicializarGrafo();
        ConjuntoTDA nodos = grafoOriginal.vertices();
        while (!nodos.conjuntoVacio()) {
            int nodo = nodos.elegir();
            nodos.sacar(nodo);
            grafoCaminos.agregarVertice(nodo);
        }
        nodos = grafoOriginal.vertices();
        while (!nodos.conjuntoVacio()) {
            int nodo = nodos.elegir();
            nodos.sacar(nodo);
            if (nodo != origen) {
                int prev = previos.recuperar(nodo);
                if (prev >= 0 && grafoOriginal.existeArista(prev, nodo)) {
                    int peso = grafoOriginal.pesoArista(prev, nodo);
                    grafoCaminos.agregarArista(prev, nodo, peso);
                }
            }
        }
        return grafoCaminos;
    }
}