public class Doctor extends Persona implements Persistible {
    private String especialidad;

    public Doctor(String id, String nombre, String especialidad) {
        super(id, nombre);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() { return especialidad; }

    @Override
    public String toCSV() { return id + "," + nombre + "," + especialidad; }

    // Reconstruye un Doctor desde una línea del CSV
    public static Doctor fromCSV(String linea) {
        String[] p = linea.split(",", 3);
        return new Doctor(p[0], p[1], p[2]);
    }

    @Override
    public String toString() {
        return "ID: " + id + "  Nombre: " + nombre + "  Especialidad: " + especialidad;
    }
}
