public class Paciente extends Persona implements Persistible {

    public Paciente(String id, String nombre) { super(id, nombre); }

    @Override
    public String toCSV() { return id + "," + nombre; }

    // Reconstruye un Paciente desde una línea del CSV
    public static Paciente fromCSV(String linea) {
        String[] p = linea.split(",", 2);
        return new Paciente(p[0], p[1]);
    }

    @Override
    public String toString() { return "ID: " + id + "  Nombre: " + nombre; }
}
