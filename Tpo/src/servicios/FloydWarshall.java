package servicios;

import java.util.*;
import interfaces.IGrafo;
import interfaces.INodoGrafo;



public class FloydWarshall {

    public static <T> void ejecutar(IGrafo<T> grafo) {

        Map<T, INodoGrafo<T>> nodos = grafo.getNodos();

        
        List<T> claves = new ArrayList<>(nodos.keySet());
        int n = claves.size();
        int[][] dist = new int[n][n];
        final int INF = 999_999;

        
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        
        for (int i = 0; i < n; i++) {
            INodoGrafo<T> nodo = nodos.get(claves.get(i));
            for (INodoGrafo<T> vecino : nodo.getVecinos()) {
                int j = claves.indexOf(vecino.getDato());
                dist[i][j] = 1; 
            }
        }

        //Aplica el Algoritmo de FloydWarshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        //Matriz resultante
        System.out.println("Matriz de distancias mínimas (FloydWarshall):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print((dist[i][j] == INF ? "INF" : dist[i][j]) + "\t");
            }
            System.out.println();
        }
    }
}
