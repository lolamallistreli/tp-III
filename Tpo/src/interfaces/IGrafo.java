package interfaces;

import java.util.*;  

public interface IGrafo<T> {

    void agregarNodo(T dato);

    INodoGrafo<T> getNodo(T dato);

    Map<T, INodoGrafo<T>> getNodos();

    void conectar(T datoOrigen, T datoDestino,int peso);

    List<T> recorrerBFS(T datoInicio);

    
    List<T> recorrerDFS(T datoInicio);

    
    int[][] obtenerMatrizAdyacencia();

    boolean esDirigido();
}
