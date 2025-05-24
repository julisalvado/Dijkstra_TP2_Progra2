package implementaciones;

import interfaces.ColaHeapTDA;

public class FilaPrioridadHeap implements ColaHeapTDA {
    class Nodo {
        int valor;
        int prioridad;
    }

    private Nodo[] heap;
    private int indice;

    @Override
    public void inicializar(int tam) {
        heap = new Nodo[tam];
        indice = 0;
    }

    @Override
    public void agregarValor(int valor, int prioridad) {
        Nodo nuevo = new Nodo();
        nuevo.valor = valor;
        nuevo.prioridad = prioridad;
        heap[indice] = nuevo;
        indice++;
        int i = indice - 1;
        while (i != 0 && heap[padre(i)].prioridad > heap[i].prioridad) {
            int p = padre(i);
            Nodo temp = heap[i];
            heap[i] = heap[p];
            heap[p] = temp;
            i = p;
        }
    }

    @Override
    public int remover() {
        if (vacio()) return -1;
        int min = heap[0].valor;
        heap[0] = heap[indice - 1];
        indice--;
        int aux = 0;
        while (true) {
            int izq = hijoIzq(aux);
            int der = hijoDer(aux);
            int menor = aux;
            if (izq < indice && heap[izq].prioridad < heap[menor].prioridad) menor = izq;
            if (der < indice && heap[der].prioridad < heap[menor].prioridad) menor = der;
            if (menor == aux)break;
            Nodo temp = heap[aux];
            heap[aux] = heap[menor];
            heap[menor] = temp;
            aux = menor;
        }
        return min;
    }

    @Override
    public int removerEn(int i) {
        i--;
        if (vacio() || i < 0 || i >= indice) return -1;
        int val = heap[i].valor;
        heap[i] = heap[indice - 1];
        indice--;
        int aux = i;
        while (true) {
            int izq = hijoIzq(aux);
            int der = hijoDer(aux);
            int menor = aux;
            if (izq < indice && heap[izq].prioridad < heap[menor].prioridad) menor = izq;
            if (der < indice && heap[der].prioridad < heap[menor].prioridad) menor = der;
            if (menor == aux) break;
            Nodo temp = heap[aux];
            heap[aux] = heap[menor];
            heap[menor] = temp;
            aux = menor;
        }
        return val;
    }

    @Override
    public int obtenerValor() {
        return heap[0].valor;
    }

    @Override
    public int obtenerPrioridad() {
        return heap[0].prioridad;
    }

    @Override
    public int obtenerValorEn(int i) {
        return heap[i].valor;
    }

    @Override
    public int obtenerPrioridadEn(int i) {
        return heap[i].prioridad;
    }

    @Override
    public boolean vacio() {
        return indice == 0;
    }

    @Override
    public int padre(int i) {
        return (i - 1) / 2;
    }

    @Override
    public int hijoIzq(int i) {
        return 2 * i + 1;
    }

    @Override
    public int hijoDer(int i) {
        return 2 * i + 2;
    }
}