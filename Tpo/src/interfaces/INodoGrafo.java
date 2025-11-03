package interfaces;

import java.util.List;

public interface INodoGrafo<T> {

    
    //PRE: el nodo debe estar inicializado.
    //POST: devuelve el dato almacenado en el nodo.
    //AXIOMA: el dato devuelto es el mismo que fue asignado por setDato().
    
    T getDato();

    
    //PRE: el parametro dato no debe ser nulo.
     //POST: el nodo almacena el nuevo dato recibido.
     //AXIOMA: despues de setDato(x), getDato() == x.

    void setDato(T dato);

    //PRE: el parametro nodo no debe ser nulo.
     //POST: el nodo pasado como parametro queda agregado a la lista de vecinos, si no existia previamente.
     // AXIOMA: si antes no existia el vecino, despues de agregarlo,getVecinos().contains(nodo) == true.

    void agregarVecino(INodoGrafo<T> nodo,int peso);

    // PRE: el parametro nodo debe existir como vecino.
    //POST: el nodo queda eliminado de la lista de vecinos si estaba presente.
    //AXIOMA: despues de eliminarlo, getVecinos().contains(nodo) == false.

    void eliminarVecino(INodoGrafo<T> nodo);

    
     // PRE: el nodo debe estar inicializado.
     // POST: devuelve una lista con todos los vecinos actuales del nodo.
     // AXIOMA: para todo nodo vecino n en getVecinos(), n es accesible directamente desde este nodo.

    List<INodoGrafo<T>> getVecinos();

    List<Integer> getPesos();
    
}
