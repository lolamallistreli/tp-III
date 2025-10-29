package modelo;


public class Persona {
    private int dni;
    private String nombre;

    public Persona(int dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    public int getDni() { return dni; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return "[" + dni + " - " + nombre + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;
        Persona p = (Persona) o;
        return this.dni == p.dni;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(dni);
    }
}
