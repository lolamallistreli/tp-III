package servicios;

import interfaces.INodoGrafo;
import modelo.Grafo;
import java.util.*;

public class Prim<T> {

    private final Grafo<T> grafo;
    private final Map<String, Integer> pesos; // clave: "origen-destino", valor: peso

    public Prim(Grafo<T> grafo) {
        this.grafo = grafo;
        this.pesos = new HashMap<>();
    }

    // Método para agregar peso a una arista existente del grafo
    public void setPeso(T origen, T destino, int peso) {
        String clave = clave(origen, destino);
        pesos.put(clave, peso);
        if (!esDirigido()) {
            pesos.put(clave(destino, origen), peso);
        }
    }

    private boolean esDirigido() {
        try {
            var field = grafo.getClass().getDeclaredField("dirigido");
            field.setAccessible(true);
            return field.getBoolean(grafo);
        } catch (Exception e) {
            return false;
        }
    }

    private String clave(T a, T b) {
        return a.toString() + "-" + b.toString();
    }

    public void ejecutarPrim(T inicio) {
        Map<T, Boolean> visitado = new HashMap<>();
        Map<T, Integer> costo = new HashMap<>();
        Map<T, T> padre = new HashMap<>();

        for (T dato : grafo.getNodos().keySet()) {
            costo.put(dato, Integer.MAX_VALUE);
            visitado.put(dato, false);
        }

        costo.put(inicio, 0);

        for (int i = 0; i < grafo.getNodos().size(); i++) {
            T u = obtenerMinimo(costo, visitado);
            if (u == null) continue;
            visitado.put(u, true);

            INodoGrafo<T> nodo = grafo.getNodo(u);
            for (INodoGrafo<T> vecino : nodo.getVecinos()) {
                T v = vecino.getDato();
                String claveUV = clave(u, v);
                int peso = pesos.getOrDefault(claveUV, Integer.MAX_VALUE);

                if (!visitado.get(v) && peso < costo.get(v)) {
                    costo.put(v, peso);
                    padre.put(v, u);
                }
            }
        }

        System.out.println("\nÁrbol de Expansión Mínima (Prim):");
        for (T nodo : padre.keySet()) {
            System.out.println("  " + padre.get(nodo) + " --(" + costo.get(nodo) + ")--> " + nodo);
        }
    }

    private T obtenerMinimo(Map<T, Integer> costo, Map<T, Boolean> visitado) {
        int min = Integer.MAX_VALUE;
        T nodoMin = null;
        for (T nodo : costo.keySet()) {
            if (!visitado.get(nodo) && costo.get(nodo) < min) {
                min = costo.get(nodo);
                nodoMin = nodo;
            }
        }
        return nodoMin;
    }
}
