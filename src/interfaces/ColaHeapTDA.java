package interfaces;

public interface ColaHeapTDA {
    void inicializar(int tam); // O(1) inicializa el heap con un maximo de elementos
    
    void agregarValor(int valor, int prioridad); // O(log n) agrega un valor al heap
    
    int remover(); // O(1) remueve el max o min del heap y devolve el valor
    int removerEn(int i); // O(log n) remueve el elemento nro i-1 y devolve el valor
    
    int obtenerValor();  // O(1) obtenes el valor del max o min del heap
    int obtenerPrioridad(); // O(1) obtenes la prioridad del max o min del heap
    int obtenerValorEn(int i); // O(1) obtenes el valor en la posicion i
    int obtenerPrioridadEn(int i); // O(1) obtenes la prioridad en la posicion i
    
    boolean vacio(); // O(1) si el heap esta vacio
    int padre(int i); // O(1) de un hijo obtengo el indice del padre
    int hijoIzq(int i); // O(1) de un padre obtengo el indice del hijo izquierdo
    int hijoDer(int i); // O(1) de un padre obtengo el indice del hijo derecho
}
