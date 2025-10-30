package interfaces;

import java.util.*;  

public interface IGrafo<T> {


    void agregarNodo(T dato);

   
    Map<Integer, ?> getNodos();

  
    void setNodos(Map<Integer, ?> nodos);


    void agregarArista(T origen, T destino);


    void bfs(int inicio);

 
    void dfs(int inicio);


    void mostrarMatrizAdyacencia();
}
