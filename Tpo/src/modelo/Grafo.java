package modelo;
import java.util.*;
import interfaces.IGrafo; 
import modelo.NodoGrafo;

public class Grafo<T> implements IGrafo<T> {
	private Map<Integer, Nodo> nodos = new HashMap<>(); 
	
	//getter y setter 
	public Map<Integer, Nodo> getNodos() {
		return nodos;
	}

	public void setNodos(Map<Integer, Nodo> nodos) {
		this.nodos = nodos;
	}

	
	public void agregarNodo(T dato) {
	    if (!nodos.containsKey(dato)) {
	        nodos.put(dato, new Nodo<T>(dato));
	    }
	}

	public void agregarArista(T origen, T destino) {
	    if (nodos.containsKey(origen) && nodos.containsKey(destino)) {
	        Nodo<T> nodoOrigen = nodos.get(origen);
	        Nodo<T> nodoDestino = nodos.get(destino);
	        nodoOrigen.agregarVecino(nodoDestino);
	        nodoDestino.agregarVecino(nodoOrigen); 
	    }
	}

	
	public void mostrarMatrizAdyacencia() {
	    System.out.println("Matriz de Adyacencia:");
	    List<Integer> claves = new ArrayList<>(nodos.keySet());
	    Collections.sort(claves);

	    // Encabezado
	    System.out.print("   ");
	    for (int k : claves) System.out.print(k + " ");
	    System.out.println();

	    // Filas
	    for (int i : claves) {
	        System.out.print(i + ": ");
	        Nodo<Integer> nodoI = nodos.get(i);
	        for (int j : claves) {
	            Nodo<Integer> nodoJ = nodos.get(j);
	            int val = (nodoI != null && nodoJ != null && nodoI.getVecinos().contains(nodoJ)) ? 1 : 0;
	            System.out.print(val + " ");
	        }
	        System.out.println();
	    }
	}
	 
	public void bfs(int inicio) {
	    if (!nodos.containsKey(inicio)) return; // precondición

	    Set<Integer> visitados = new HashSet<>();
	    Queue<Nodo<Integer>> cola = new LinkedList<>();

	    Nodo<Integer> nodoInicio = nodos.get(inicio);
	    cola.add(nodoInicio);
	    visitados.add(inicio);

	    System.out.println("Recorrido BFS:");
	    while (!cola.isEmpty()) {
	        Nodo<Integer> actual = cola.poll();
	        System.out.print(actual.getValor() + " ");
	        for (Nodo<Integer> vecino : actual.getVecinos()) {
	            if (!visitados.contains(vecino.getValor())) {
	                visitados.add(vecino.getValor());
	                cola.add(vecino);
	            }
	        }
	    }
	    System.out.println();
	}
	
	public void dfs(int inicio) {
	    if (!nodos.containsKey(inicio)) return;

	    Set<Integer> visitados = new HashSet<>();
	    System.out.println("Recorrido DFS:");
	    dfsRec(nodos.get(inicio), visitados);
	    System.out.println();
	}

	private void dfsRec(Nodo<Integer> actual, Set<Integer> visitados) {
	    visitados.add(actual.getValor());
	    System.out.print(actual.getValor() + " ");

	    List<Nodo<Integer>> vecinos = actual.getVecinos();
	    for (int i = vecinos.size() - 1; i >= 0; i--) {
	        Nodo<Integer> vecino = vecinos.get(i);
	        if (!visitados.contains(vecino.getValor())) {
	            dfsRec(vecino, visitados);
	        }
	    }
	}

	
	}


