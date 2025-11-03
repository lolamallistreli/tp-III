package servicios;

import interfaces.INodoGrafo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import modelo.Grafo;


public class Kruskal {
    public static <T> void ejecutar(Grafo<T> grafo) {
        
        Map<T, INodoGrafo<T>> nodos = grafo.getNodos();
        if (nodos == null || nodos.isEmpty()) {
            System.out.println("Error: El grafo está vacío.");
            return;
        }

        ///crea la lista de todas las aristas únicas
        List<Arista<T>> aristas = new ArrayList<>();
        Set<String> aristasVistas = new HashSet<>(); 

        Map<T, Integer> nodeToId = new HashMap<>();
        int idCounter = 0;
        for (T datoNodo : nodos.keySet()) {
            nodeToId.put(datoNodo, idCounter++);
        }

        for (INodoGrafo<T> nodo : nodos.values()) {
            List<INodoGrafo<T>> vecinos = nodo.getVecinos();
            List<Integer> pesos = nodo.getPesos(); //nodo tiene pesos

            for (int i = 0; i < vecinos.size(); i++) {
                T origenDato = nodo.getDato();
                T destinoDato = vecinos.get(i).getDato();
                int peso = pesos.get(i);
                int origenId = nodeToId.get(origenDato);
                int destinoId = nodeToId.get(destinoDato);
                String clave = Math.min(origenId, destinoId) + "-" + Math.max(origenId, destinoId);

                if (aristasVistas.add(clave)) { 
                    aristas.add(new Arista<>(origenDato, destinoDato, peso));
                }
            }
        }

        ///ordena las aristas por peso
        aristas.sort(Comparator.comparingInt(a -> a.peso));

        UnionFind uf = new UnionFind(nodos.size());
        List<Arista<T>> mst = new ArrayList<>(); 
        int costoTotal = 0;

        for (Arista<T> arista : aristas) {
            int idOrigen = nodeToId.get(arista.origen);
            int idDestino = nodeToId.get(arista.destino);
            if (uf.union(idOrigen, idDestino)) {
                mst.add(arista); 
                costoTotal += arista.peso;
            }
        }

        System.out.println("--- Árbol de Expansión Mínima (Kruskal) ---");
        for (Arista<T> a : mst) {
            System.out.println("  " + a.origen + " --(" + a.peso + ")-- " + a.destino);
        }
        System.out.println("Costo total del MST: " + costoTotal);
    }
    private static class Arista<T> {
        T origen, destino;
        int peso;

        Arista(T o, T d, int p) {
            this.origen = o;
            this.destino = d;
            this.peso = p;
        }
    }
    private static class UnionFind {
        private int[] padre;

        UnionFind(int n) {
            padre = new int[n];
            for (int i = 0; i < n; i++) {
                padre[i] = i; 
            }
        }
        int find(int x) {
            if (padre[x] == x) {
                return x;
            }
            padre[x] = find(padre[x]); 
            return padre[x];
        }
        boolean union(int x, int y) {
            int raizX = find(x);
            int raizY = find(y);

            if (raizX != raizY) {
                padre[raizX] = raizY; 
                return true;
            }
            return false; 
        }
    }
}
