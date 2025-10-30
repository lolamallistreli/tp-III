package modelo;

import java.util.HashMap;
import java.util.Map;
import interfaces.IGrafo;
import interfaces.INodoGrafo;


public class Grafo<T> implements IGrafo<T> { 
    
    private Map<T, INodoGrafo<T>> nodos;
    private final boolean dirigido;

    public Grafo(boolean dirigido) {
        this.dirigido = dirigido;
        this.nodos = new HashMap<>();
    }
}
    

