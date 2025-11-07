package interfaces;

import java.util.*;  

public interface IGrafo<T> {

    //PRE: el dato no debe ser nulo.
    //POST: agrega un nuevo nodo al grafo con el dato especificado, si no existia previamente.
    //AXIOMA: si el nodo no existia antes, luego de agregarlo getNodos().containsKey(dato) == true.
    void agregarNodo(T dato);

    //PRE: el dato debe existir en el grafo.
    //POST: devuelve el nodo correspondiente al dato recibido.
    //AXIOMA: si el nodo fue agregado previamente con agregarNodo(dato), getNodo(dato) != null.
    INodoGrafo<T> getNodo(T dato);

    //PRE: el grafo debe estar inicializado.
    //POST: devuelve un mapa con todos los nodos del grafo.
    //AXIOMA: para todo dato agregado, getNodos().containsKey(dato) == true.
    Map<T, INodoGrafo<T>> getNodos();

    //PRE: ambos datos (origen y destino) deben existir en el grafo.
    //POST: conecta los dos nodos con una arista de peso indicado. Si el grafo no es dirigido, la conexión es bidireccional.
    //AXIOMA: si el grafo es dirigido, solo origen tiene como vecino a destino;si no es dirigido, ambos se listan como vecinos mutuamente.
    void conectar(T datoOrigen, T datoDestino, int peso);

    //PRE: el nodo de inicio debe existir en el grafo.
    //POST: devuelve una lista con los datos recorridos en orden BFS (anchura).
    //AXIOMA: todos los nodos alcanzables desde el nodo inicial aparecen exactamente una vez en la lista resultante.
    List<T> recorrerBFS(T datoInicio);

    //PRE: el nodo de inicio debe existir en el grafo.
    //POST: devuelve una lista con los datos recorridos en orden DFS (profundidad).
    //AXIOMA: todos los nodos alcanzables desde el nodo inicial aparecen exactamente una vez en la lista resultante.
    List<T> recorrerDFS(T datoInicio);

    //PRE: el grafo debe tener al menos un nodo.
    //POST: devuelve una matriz cuadrada NxN que representa las conexiones (adyacencias) entre nodos.
    //AXIOMA: matriz[i][j] == 1 si existe conexión entre los nodos i y j; de lo contrario, matriz[i][j] == 0.
    int[][] obtenerMatrizAdyacencia();

    //PRE: el grafo debe estar inicializado.
    //POST: devuelve true si el grafo es dirigido; false en caso contrario.
    //AXIOMA: si esDirigido() == true, las conexiones se almacenan en un solo sentido (origen → destino).
    boolean esDirigido();
}
