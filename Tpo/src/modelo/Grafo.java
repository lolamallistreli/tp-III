package modelo;
import java.util.*;
import interfaces.IGrafo;
import interfaces.INodoGrafo;

public class Grafo<T> implements IGrafo<T> {

	private Map<T, INodoGrafo<T>> nodos; 
	private final boolean dirigido;

	public Grafo(boolean dirigido) {
        this.nodos = new HashMap<>();
        this.dirigido = dirigido;
    }


	//getter y setter 
	@Override
    public Map<T, INodoGrafo<T>> getNodos() {
        return nodos;
    }
    
    @Override
    public INodoGrafo<T> getNodo(T dato) {
        return nodos.get(dato);
    }

    @Override
    public void agregarNodo(T dato) {
        // CORREGIDO: Ahora 'dato' (tipo T) funciona como clave.
        if (!nodos.containsKey(dato)) {
            // Usa la implementación 'NodoGrafo' que ya tenías.
            nodos.put(dato, new NodoGrafo<>(dato)); 
        }
    }

    @Override
    public void conectar(T datoOrigen, T datoDestino) {
        INodoGrafo<T> origen = getNodo(datoOrigen);
        INodoGrafo<T> destino = getNodo(datoDestino);

        if (origen != null && destino != null) {
            origen.agregarVecino(destino);
            // Si no es dirigido, la conexión es mutua
            if (!dirigido) {
                destino.agregarVecino(origen);
            }
        }
    }

    @Override
    public int[][] obtenerMatrizAdyacencia() {
        // Mantenemos la lógica de la matriz, pero usando T
        List<INodoGrafo<T>> listaNodos = new ArrayList<>(nodos.values());
        Map<INodoGrafo<T>, Integer> indiceNodos = new HashMap<>();
        
        for (int i = 0; i < listaNodos.size(); i++) {
            indiceNodos.put(listaNodos.get(i), i);
        }

        int N = listaNodos.size();
        int[][] matriz = new int[N][N];

        for (int i = 0; i < N; i++) {
            INodoGrafo<T> nodoOrigen = listaNodos.get(i);
            for (INodoGrafo<T> vecino : nodoOrigen.getVecinos()) {
                if (indiceNodos.containsKey(vecino)) {
                    int j = indiceNodos.get(vecino);
                    matriz[i][j] = 1;
                }
            }
        }
        return matriz;
    }

    @Override
    public List<T> recorrerBFS(T datoInicio) {
        List<T> resultado = new ArrayList<>();
        INodoGrafo<T> inicio = getNodo(datoInicio);
        if (inicio == null) return resultado;

        Set<INodoGrafo<T>> visitados = new HashSet<>();
        Queue<INodoGrafo<T>> cola = new LinkedList<>();

        visitados.add(inicio);
        cola.add(inicio);

        while (!cola.isEmpty()) {
            INodoGrafo<T> actual = cola.poll();
            resultado.add(actual.getDato()); // Agregamos el dato (Persona) a la lista

            for (INodoGrafo<T> vecino : actual.getVecinos()) {
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.add(vecino);
                }
            }
        }
        return resultado;
    }

    @Override
    public List<T> recorrerDFS(T datoInicio) {
        List<T> resultado = new ArrayList<>();
        INodoGrafo<T> inicio = getNodo(datoInicio);
        if (inicio == null) return resultado;

        Set<INodoGrafo<T>> visitados = new HashSet<>();
        Stack<INodoGrafo<T>> pila = new Stack<>();

        pila.push(inicio);

        while (!pila.isEmpty()) {
            INodoGrafo<T> actual = pila.pop();

            if (!visitados.contains(actual)) {
                visitados.add(actual);
                resultado.add(actual.getDato());
                
                // Invertimos el orden para un DFS iterativo estándar
                List<INodoGrafo<T>> vecinos = new ArrayList<>(actual.getVecinos());
                Collections.reverse(vecinos); 
                
                for (INodoGrafo<T> vecino : vecinos) {
                    if (!visitados.contains(vecino)) {
                        pila.push(vecino);
                    }
                }
            }
        }
        return resultado;
    }
}