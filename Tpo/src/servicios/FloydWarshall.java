package servicios;

import interfaces.IGrafo;
import interfaces.INodoGrafo;
import java.util.*;

public class FloydWarshall {

    public static <T> void ejecutar(IGrafo<T> grafo) {
        Map<T, INodoGrafo<T>> nodos = grafo.getNodos();
        List<T> claves = new ArrayList<>(nodos.keySet());
        int n = claves.size();
        final int INF = 999_999;

        int[][] dist = new int[n][n];

        //Inicialización
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        //Cargar pesos del grafo
        for (int i = 0; i < n; i++) {
            INodoGrafo<T> nodo = nodos.get(claves.get(i));
            List<INodoGrafo<T>> vecinos = nodo.getVecinos();
            List<Integer> pesos = nodo.getPesos();

            for (int k = 0; k < vecinos.size(); k++) {
                INodoGrafo<T> vecino = vecinos.get(k);
                int j = claves.indexOf(vecino.getDato());
                if (j != -1) {
                    dist[i][j] = pesos.get(k);
                }
            }
        }

        //Mostrar matriz de costos originales
        System.out.println("Matriz de costos originales:");
        imprimirMatriz(dist, claves, INF);

        // Aplicar Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF
                            && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        //Mostrar matriz resultante
        System.out.println("\nMatriz de distancias minimas (FloydWarshall):");
        imprimirMatriz(dist, claves, INF);
        

    }

    private static <T> void imprimirMatriz(int[][] dist, List<T> claves, int INF) {
        System.out.print("\t");
        for (T clave : claves) {
            System.out.print(clave.toString() + "\t");
        }
        System.out.println();

        for (int i = 0; i < claves.size(); i++) {
            System.out.print(claves.get(i).toString() + "\t");
            for (int j = 0; j < claves.size(); j++) {
                System.out.print((dist[i][j] == INF ? "INF" : dist[i][j]) + "\t");
            }
            System.out.println();
        }
    }
}
