package servicios;

import java.util.*;
import interfaces.INodo;
import modelo.Grafo;
import modelo.Nodo;

public class Dijkstra {
    public static <T> void ejecutar(Grafo<T> grafo, T origen) {
        Map<T, Nodo<T>> nodos = grafo.getNodos();
        if (nodos == null || nodos.isEmpty()) {
            System.out.println("Grafo vacío.");
            return;
        }
        if (!nodos.containsKey(origen)) {
            System.out.println("El nodo origen " + origen + " no existe.");
            return;
        }
        Map<T, Integer> distancias = new HashMap<>();
        Set<T> visitados = new HashSet<>();
        PriorityQueue<NodoDistancia<T>> cola = new PriorityQueue<>();
        for (T clave : nodos.keySet()) {
            distancias.put(clave, Integer.MAX_VALUE);
        }
        distancias.put(origen, 0);
        cola.add(new NodoDistancia<>(origen, 0));

        // Bucle principal
        while (!cola.isEmpty()) {
            NodoDistancia<T> nd = cola.poll();
            T u = nd.id;

            if (visitados.contains(u)) continue;

            int distU = distancias.get(u);
            if (distU == Integer.MAX_VALUE) continue; 

            visitados.add(u);

            Nodo<T> nodoU = nodos.get(u);
            if (nodoU == null) continue;

            List<INodo<T>> vecinos = nodoU.getVecinos();
            List<Integer> pesos     = nodoU.getPesos();

            int m = Math.min(vecinos.size(), pesos.size());
            for (int i = 0; i < m; i++) {
                T v    = vecinos.get(i).getValor();
                int w  = pesos.get(i);
                int nv = distU + w;
                if (nv < distancias.get(v)) {
                    distancias.put(v, nv);
                    cola.add(new NodoDistancia<>(v, nv));
                }
            }
        }

        // Salida ordenada por toString() de T (si querés otro orden, proveé un Comparator)
        System.out.println("Distancias mínimas desde " + origen + ":");
        List<T> orden = new ArrayList<>(distancias.keySet());
        orden.sort(Comparator.comparing(Object::toString));
        for (T k : orden) {
            int d = distancias.get(k);
            System.out.println("A " + k + " = " + (d == Integer.MAX_VALUE ? "∞" : d));
        }
    }

    // Par (id, distancia) para la cola
    private static class NodoDistancia<T> implements Comparable<NodoDistancia<T>> {
        final T id;
        final int distancia;
        NodoDistancia(T id, int distancia) { this.id = id; this.distancia = distancia; }
        @Override public int compareTo(NodoDistancia<T> o) { return Integer.compare(this.distancia, o.distancia); }
    }
}