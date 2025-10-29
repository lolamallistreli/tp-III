public interface INodoGrafo<T> {
    T getDato();
    void setDato(T dato);

    void agregarVecino(INodoGrafo<T> nodo);
    void eliminarVecino(INodoGrafo<T> nodo);

    java.util.List<INodoGrafo<T>>getVecinos()
}