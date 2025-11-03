package modelo;

import interfaces.INodoGrafo;
import java.util.ArrayList;
import java.util.List;

public class NodoGrafo<T> implements INodoGrafo<T> {
    private T dato;
    private final  List<INodoGrafo<T>> vecinos;
    private final  List<Integer> pesos;

    public NodoGrafo(T dato) {
        this.dato = dato;
        this.vecinos = new ArrayList<>();
        this.pesos = new ArrayList<>();
    }

    @Override
    public T getDato() {
        return dato;
    }

    @Override
    public void setDato(T dato) {
        this.dato = dato;
    }

    @Override
    public void agregarVecino(INodoGrafo<T> nodo, int peso) {
        if (!vecinos.contains(nodo)) {
            vecinos.add(nodo);
            pesos.add(peso);
        }
    }

    @Override
    public void eliminarVecino(INodoGrafo<T> nodo) {
        int index = vecinos.indexOf(nodo);
        if (index != -1) {
            vecinos.remove(index);
            pesos.remove(index);
        }
    }

    @Override
    public List<INodoGrafo<T>> getVecinos() {
        return vecinos;
    }

    @Override
    public List<Integer> getPesos() {
        return pesos;
    }

    @Override
    public String toString() {
        return dato.toString();
    }
}
