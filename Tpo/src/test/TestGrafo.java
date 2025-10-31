package test;

import java.util.Arrays;
import java.util.List;

import interfaces.IGrafo;
import modelo.Grafo;
import modelo.Persona;
//import servicios.Dijkstra;
import servicios.FloydWarshall;
//import servicios.Kruskal;
import servicios.Prim;


public class TestGrafo {
    public static void main(String[] args) {

        Persona p1 = new Persona(100,"Valentina");
        Persona p2 = new Persona(101,"Morena");
        Persona p3 = new Persona(102,"Lola");
        Persona p4 = new Persona(103,"Cielo");
        Persona p5 = new Persona(104,"Sofia");


        //1. GRAFO NO DIRIGIDO
        System.out.println("--- GRAFO NO DIRIGIDO ---");
        
        IGrafo<Persona> grafoNoDirigido = new Grafo<>(false); 
        
        grafoNoDirigido.agregarNodo(p1);
        grafoNoDirigido.agregarNodo(p2);
        grafoNoDirigido.agregarNodo(p3);
        grafoNoDirigido.agregarNodo(p4);

        ///Conexiones: p1<->p2 (Valentina<->Morena), p2<->p3, p1<->p4
        grafoNoDirigido.conectar(p1, p2); 
        grafoNoDirigido.conectar(p2, p3); 
        grafoNoDirigido.conectar(p1, p4); 

        ///Test DFS (Valentina)
        List<Persona> dfsNoDir = grafoNoDirigido.recorrerDFS(p1);
        System.out.println("  1. DFS (Valentina): " + dfsNoDir);

        //test BFS(Valentina)
        List<Persona> bfsNoDir = grafoNoDirigido.recorrerBFS(p1);
        System.out.println("  2. BFS (Valentina): " + bfsNoDir);

        ////test Matriz
        System.out.println("  3. Matriz de Adyacencia (No Dirigido):");
        imprimirMatriz(grafoNoDirigido.obtenerMatrizAdyacencia());

        
        // 2. GRAFO DIRIGIDO 
        System.out.println("\n--- GRAFO DIRIGIDO ---");
        IGrafo<Persona> grafoDirigido = new Grafo<>(true); 
        
        grafoDirigido.agregarNodo(p1);
        grafoDirigido.agregarNodo(p2);
        grafoDirigido.agregarNodo(p3);
        grafoDirigido.agregarNodo(p5); 

        // Valentina sigue a Morena
        grafoDirigido.conectar(p1, p2);
        grafoDirigido.conectar(p1, p3);
        grafoDirigido.conectar(p2, p5);

        //test DFS (Valentina)
        List<Persona> dfsDir = grafoDirigido.recorrerDFS(p1);
        System.out.println("  1. DFS (Valentina): " + dfsDir); 

        ///test BFS 
        List<Persona> bfsDir = grafoDirigido.recorrerBFS(p1);
        System.out.println("  2. BFS (Valentina): " + bfsDir); 

        ///Test Matriz
        System.out.println("  3. Matriz de Adyacencia (Dirigido):");
        imprimirMatriz(grafoDirigido.obtenerMatrizAdyacencia());
    
        //ALGORITMOS
        System.out.println("\n--- ALGORITMOS ---");
        System.out.println("Algoritmo FloydWarshall sobre el grafo no dirigido:");
        FloydWarshall.ejecutar(grafoNoDirigido);

        /* 
        System.out.println("\n--- ALGORITMOS ---");
        System.out.println("Algoritmo Kruskal sobre el grafo no dirigido:");
        FloydWarshall.ejecutar(grafoNoDirigido);

        System.out.println("\n--- ALGORITMOS ---");
        System.out.println("Algoritmo Prim sobre el grafo no dirigido:");
        FloydWarshall.ejecutar(grafoNoDirigido);

        System.out.println("\n--- ALGORITMOS ---");
        System.out.println("Algoritmo Dijkstra sobre el grafo no dirigido:");
        FloydWarshall.ejecutar(grafoNoDirigido);
        System.out.println("\n--- ALGORITMOS ---");
        System.out.println("Algoritmo Dijkstra sobre el grafo no dirigido:");
        FloydWarshall.ejecutar(grafoNoDirigido);*/

        

}
    
    
    public static void imprimirMatriz(int[][] matriz) {
        for (int[] fila : matriz) {
            System.out.println("     " + Arrays.toString(fila));
        }

    System.out.println("\nExplicacion Final: Para que el grafo sea dirigido, se indica 'true' al crearlo");
    System.out.println("En ese caso, las aristas tienen un solo sentido y la matriz no es simetrica");
    System.out.println("Los recorridos DFS y BFS se pueden hacer igual, respetando la direccion de las conexiones");

    
}
}
