package interfaces;

public interface IGrafo<T> {
	
		void agregarNodo(int valor); 
	    void agregarArista(int origen, int destino); 
	    void mostrarMatrizAdyacencia(); 
	    void mostrarListaAdyacencia(); 	    
	    void bfs(int inicio); 
	    void dfs(int inicio); 

}
