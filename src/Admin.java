public class Admin extends Persona {
    private String password;

    public Admin(String id, String nombre, String password) {
        super(id, nombre);
        this.password = password;
    }

    public boolean verificarPassword(String pwd) { return this.password.equals(pwd); }

    public String toCSV() { return id + "," + nombre + "," + password; }

    public static Admin fromCSV(String linea) {
        String[] p = linea.split(",", 3);
        return new Admin(p[0], p[1], p[2]);
    }
}
