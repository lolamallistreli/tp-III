package servicios;

import interfaces.INodoGrafo;
import java.util.*;
import modelo.Grafo;


public class Prim<T> {

    private final Grafo<T> grafo;
    
    public Prim(Grafo<T> grafo) {
        this.grafo = grafo;
    }


    public void ejecutarPrim(T inicio) {

        if (grafo.esDirigido()) {
            System.out.println("ADVERTENCIA: Prim se ejecuta sobre un grafo NO dirigido. Los resultados pueden ser inconsistentes.");
        }
        
        Map<T, Integer> costo = new HashMap<>();
        Map<T, T> padre = new HashMap<>();

        PriorityQueue<NodoCosto<T>> pq = new PriorityQueue<>();

        for (T dato : grafo.getNodos().keySet()) {
            costo.put(dato, Integer.MAX_VALUE);

        }

        costo.put(inicio, 0);
        pq.add(new NodoCosto<>(inicio, 0));

        
        while (!pq.isEmpty()) {
            T u = pq.poll().nodo; 

            ///si. el costo actual en la cola es mayor que el costo ya encontrado, ignorar
            if (costo.get(u) < 0) continue; 
            costo.put(u, -1); 

            INodoGrafo<T> nodoU = grafo.getNodo(u);
            List<INodoGrafo<T>> vecinos = nodoU.getVecinos();
            List<Integer> pesos = nodoU.getPesos(); 

            
            for (int i = 0; i < vecinos.size(); i++) {
                T v = vecinos.get(i).getDato();
                int peso = pesos.get(i);
                
                
                if (costo.get(v) != null && costo.get(v) >= 0 && peso < costo.get(v)) { 
                    costo.put(v, peso);
                    padre.put(v, u);
                    pq.add(new NodoCosto<>(v, peso));
                }
            }
        }
        
        
        int costoTotal = 0;
        System.out.println("\n--- Árbol de Expansión Mínima (Prim - Optimizado) ---");
        for (T nodo : padre.keySet()) {
            T padreNodo = padre.get(nodo);
            
            ///padreNodo ->nodo
            INodoGrafo<T> nodoPadre = grafo.getNodo(padreNodo);
            
            
            int indiceVecino = nodoPadre.getVecinos().indexOf(grafo.getNodo(nodo));
            
            if (indiceVecino != -1) {
                int pesoReal = nodoPadre.getPesos().get(indiceVecino);
                costoTotal += pesoReal;
                System.out.println("  " + padreNodo + " --(" + pesoReal + ")-- " + nodo);
            }
        }
        System.out.println("Costo total del MST: " + costoTotal);
    }
    
    

    ///clase auxiliar para la PriorityQueue (almacena el nodo y el costo)
    private static class NodoCosto<T> implements Comparable<NodoCosto<T>> {
        T nodo;
        int costo;

        NodoCosto(T nodo, int costo) {
            this.nodo = nodo;
            this.costo = costo;
        }

        @Override
        public int compareTo(NodoCosto<T> otro) {
            return Integer.compare(this.costo, otro.costo);
        }
    }
}

