// Clase base para Doctor, Paciente y Admin
public abstract class Persona {
    protected String id;
    protected String nombre;

    public Persona(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId()     { return id; }
    public String getNombre() { return nombre; }
}
