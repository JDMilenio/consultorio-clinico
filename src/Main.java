public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        try {
            sistema.cargarDatos();
        } catch (Exception e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
        new Menu(sistema).iniciar();
    }
}
