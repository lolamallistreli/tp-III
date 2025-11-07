package interfaces;

public interface IPersona {

    //PRE: el objeto Persona debe estar inicializado correctamente.
    //POST: devuelve el número de DNI asociado a la persona.
    //AXIOMA: el valor retornado por getDni() es inmutable durante la vida del objeto.
    int getDni();

    //PRE: el objeto Persona debe estar inicializado correctamente.
    //POST: devuelve el nombre asociado a la persona.
    //AXIOMA: el valor retornado por getNombre() es el mismo que se pasó al constructor.
    String getNombre();

    //PRE: el objeto Persona debe estar correctamente inicializado.
    //POST: devuelve una representación en texto del objeto Persona.
    //AXIOMA: el resultado de toString() siempre tiene el formato "[dni - nombre]".
    String toString();

    //PRE: el objeto comparado no debe ser nulo.
    //POST: devuelve true si el DNI de ambas personas coincide.
    //AXIOMA: si p1.equals(p2) == true, entonces p1.hashCode() == p2.hashCode().
    boolean equals(Object o);

    //PRE: la persona debe estar inicializada.
    //POST: devuelve un codigo hash basado únicamente en el DNI.
    //AXIOMA: dos personas con el mismo DNI tendrán el mismo hashCode().
    int hashCode();
}
