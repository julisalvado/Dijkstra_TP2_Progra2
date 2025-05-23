package implementaciones;

import interfaces.DiccionarioSimpleTDA;
import interfaces.GrafoTDA;
import interfaces.ColaHeapTDA;
import interfaces.ConjuntoTDA;

public class Dijkstra {

    public static class GrafosResultado {
        public GrafoTDA grafoCostos;
        public GrafoTDA grafoCaminos;
    }

    public static GrafosResultado resolver(GrafoTDA grafoOriginal, int origen) {
        
        ConjuntoTDA nodos = grafoOriginal.vertices();
        
        DiccionarioSimpleTDA distancias = new DicSimpleA(); //costo minimo desde el origen a cada nodo
        distancias.inicializarDiccionario();

        DiccionarioSimpleTDA previos = new DicSimpleA();
        previos.inicializarDiccionario();

        ColaHeapTDA cola = new FilaPrioridadHeap();
        cola.inicializar(100);

        //armamos copia de nodos
        ConjuntoTDA copiaNodos2 = new ConjuntoAR(); 
        copiaNodos2.inicializarConjunto();

        while (!nodos.conjuntoVacio()) {
        int nodo = nodos.elegir();
        copiaNodos2.agregar(nodo); 
        nodos.sacar(nodo);   

        //llenamos las distancias iniciales (el 0 y los infinitos)
        if (nodo == origen) {
            distancias.agregar(nodo, 0);
            cola.agregarValor(nodo, 0);
        } else {
            distancias.agregar(nodo, Integer.MAX_VALUE);
            cola.agregarValor(nodo, Integer.MAX_VALUE);
        }
        }

        while (!copiaNodos2.conjuntoVacio()) {
            int nodo = copiaNodos2.elegir();
            nodos.agregar(nodo);
            copiaNodos2.sacar(nodo);
        }

        while (!cola.vacio()) {
            int prioridadU = cola.obtenerPrioridad();
            int u = cola.remover();

            if (distancias.recuperar(u) < prioridadU) {
                continue; 
            }

            ConjuntoTDA vertices = grafoOriginal.vertices();
            ConjuntoTDA copiaVertices = new ConjuntoAR();
            copiaVertices.inicializarConjunto();

            while (!vertices.conjuntoVacio()) {
                int v = vertices.elegir();
                copiaVertices.agregar(v);
                vertices.sacar(v);

                if (grafoOriginal.existeArista(u, v)) {
                    int pesoUV = grafoOriginal.pesoArista(u, v);
                    int distU = distancias.recuperar(u);
                    int distV = distancias.recuperar(v);
                    int nuevaDist = distU + pesoUV;

                    if (nuevaDist < distV) {
                        distancias.agregar(v, nuevaDist);
                        previos.agregar(v, u);
                        cola.agregarValor(v, nuevaDist);
                    }
                }
            }

            while (!copiaVertices.conjuntoVacio()) {
                int v = copiaVertices.elegir();
                vertices.agregar(v);
                copiaVertices.sacar(v);
            }

            
        }

        //armo grafoCostos
        GrafoTDA grafoCostos = new GrafoMA();
        grafoCostos.inicializarGrafo();

        ConjuntoTDA copiaVertices2 = new ConjuntoAR();
        copiaVertices2.inicializarConjunto();
        ConjuntoTDA vertices = grafoOriginal.vertices();

        while (!vertices.conjuntoVacio()) {
            int nodo = vertices.elegir();
            copiaVertices2.agregar(nodo);
            vertices.sacar(nodo);
            grafoCostos.agregarVertice(nodo);
        }


        while (!copiaVertices2.conjuntoVacio()) {
            int nodo = copiaVertices2.elegir();
            copiaVertices2.sacar(nodo);

            if (nodo != origen && distancias.recuperar(nodo) < Integer.MAX_VALUE) {
                grafoCostos.agregarArista(origen, nodo, distancias.recuperar(nodo));
            }
        }

      

        //armo grafoCaminos
        GrafoTDA grafoCaminos = new GrafoMA();
        grafoCaminos.inicializarGrafo();

        ConjuntoTDA copiaVertices3 = new ConjuntoAR();
        copiaVertices3.inicializarConjunto();
        ConjuntoTDA vertices2 = grafoOriginal.vertices();

        while (!vertices2.conjuntoVacio()) {
            int nodo = vertices2.elegir();
            copiaVertices3.agregar(nodo);
            vertices2.sacar(nodo);

            grafoCaminos.agregarVertice(nodo);
        }

        ConjuntoTDA copiaVerticesAux = new ConjuntoAR();
        copiaVerticesAux.inicializarConjunto();

        while (!copiaVertices3.conjuntoVacio()) {
            int nodo = copiaVertices3.elegir();
            copiaVerticesAux.agregar(nodo);
            copiaVertices3.sacar(nodo);

            if (nodo != origen) {
                int prev = previos.recuperar(nodo);
                if (prev >= 0 && grafoOriginal.existeArista(prev, nodo)) {
                    int peso = grafoOriginal.pesoArista(prev, nodo);
                    grafoCaminos.agregarArista(prev, nodo, peso);
                }
            }
        }


        while (!copiaVerticesAux.conjuntoVacio()) {
            int nodo = copiaVerticesAux.elegir();
            vertices2.agregar(nodo);
            copiaVerticesAux.sacar(nodo);
        }


        GrafosResultado resultado = new GrafosResultado();
        resultado.grafoCostos = grafoCostos;
        resultado.grafoCaminos = grafoCaminos;
        return resultado;
    }
}