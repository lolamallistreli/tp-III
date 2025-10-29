package modelo;

import interfaces.INodoGrafo;
import java.util.ArrayList;
import java.util.List;

public class NodoGrafo<T> implements INodoGrafo<T> {
    private T dato;
    private List<INodoGrafo<T>> vecinos;

    public NodoGrafo(T dato) {
        this.dato = dato;
        this.vecinos = new ArrayList<>();
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
    public void agregarVecino(INodoGrafo<T> nodo) {
        if (!vecinos.contains(nodo)) {
            vecinos.add(nodo);
        }
    }

    @Override
    public void eliminarVecino(INodoGrafo<T> nodo) {
        vecinos.remove(nodo);
    }

    @Override
    public List<INodoGrafo<T>> getVecinos() {
        return vecinos;
    }

    @Override
    public String toString() {
        return dato.toString();
    }
}
