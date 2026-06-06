import java.util.Scanner;

public class Menu {
    private final Sistema sistema;
    private final Scanner sc = new Scanner(System.in);

    public Menu(Sistema sistema) { this.sistema = sistema; }

    public void iniciar() {
        System.out.println("=== Sistema de Administracion de Citas ===");
        if (!autenticar()) return;
        menuPrincipal();
    }

    // Permite hasta 3 intentos fallidos antes de cerrar
    private boolean autenticar() {
        for (int i = 0; i < 3; i++) {
            System.out.print("ID: ");
            String id  = sc.nextLine().trim();
            System.out.print("Contrasena: ");
            String pwd = sc.nextLine().trim();
            if (sistema.autenticar(id, pwd) != null) {
                System.out.println("Bienvenido.\n");
                return true;
            }
            System.out.println("Credenciales incorrectas. Intento " + (i + 1) + "/3\n");
        }
        System.out.println("Acceso denegado.");
        return false;
    }

    private void menuPrincipal() {
        while (true) {
            System.out.println("--- MENU PRINCIPAL ---");
            System.out.println("1. Doctores  2. Pacientes  3. Citas  4. Salir");
            System.out.print("Opcion: ");
            switch (sc.nextLine().trim()) {
                case "1" -> menuDoctores();
                case "2" -> menuPacientes();
                case "3" -> menuCitas();
                case "4" -> { System.out.println("Hasta pronto!"); return; }
                default  -> System.out.println("Opcion invalida.\n");
            }
        }
    }

    private void menuDoctores() {
        System.out.println("\n-- Doctores --");
        System.out.println("1. Listar  2. Dar de alta  3. Eliminar  0. Regresar");
        System.out.print("Opcion: ");
        switch (sc.nextLine().trim()) {
            case "1" -> {
                if (sistema.getDoctores().isEmpty())
                    System.out.println("Sin doctores registrados.");
                else sistema.getDoctores().forEach(System.out::println);
            }
            case "2" -> {
                try {
                    System.out.print("ID: ");
                    String id  = sc.nextLine().trim();
                    System.out.print("Nombre: ");
                    String nom = sc.nextLine().trim();
                    System.out.print("Especialidad: ");
                    String esp = sc.nextLine().trim();
                    sistema.agregarDoctor(new Doctor(id, nom, esp));
                    System.out.println("Doctor registrado.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            case "3" -> {
                try {
                    System.out.print("ID a eliminar: ");
                    String id = sc.nextLine().trim();
                    System.out.println(sistema.eliminarDoctor(id)
                        ? "Doctor eliminado." : "No encontrado.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        System.out.println();
    }

    private void menuPacientes() {
        System.out.println("\n-- Pacientes --");
        System.out.println("1. Listar  2. Dar de alta  3. Eliminar  0. Regresar");
        System.out.print("Opcion: ");
        switch (sc.nextLine().trim()) {
            case "1" -> {
                if (sistema.getPacientes().isEmpty())
                    System.out.println("Sin pacientes registrados.");
                else sistema.getPacientes().forEach(System.out::println);
            }
            case "2" -> {
                try {
                    System.out.print("ID: ");
                    String id  = sc.nextLine().trim();
                    System.out.print("Nombre: ");
                    String nom = sc.nextLine().trim();
                    sistema.agregarPaciente(new Paciente(id, nom));
                    System.out.println("Paciente registrado.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            case "3" -> {
                try {
                    System.out.print("ID a eliminar: ");
                    String id = sc.nextLine().trim();
                    System.out.println(sistema.eliminarPaciente(id)
                        ? "Paciente eliminado." : "No encontrado.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        System.out.println();
    }

    private void menuCitas() {
        System.out.println("\n-- Citas --");
        System.out.println("1. Listar  2. Crear  3. Cancelar  0. Regresar");
        System.out.print("Opcion: ");
        switch (sc.nextLine().trim()) {
            case "1" -> {
                if (sistema.getCitas().isEmpty())
                    System.out.println("Sin citas registradas.");
                else sistema.getCitas().forEach(System.out::println);
            }
            case "2" -> {
                try {
                    System.out.print("ID cita: ");
                    String id  = sc.nextLine().trim();
                    System.out.print("Fecha/hora (YYYY-MM-DD HH:MM): ");
                    String fh  = sc.nextLine().trim();
                    System.out.print("Motivo: ");
                    String mot = sc.nextLine().trim();
                    System.out.print("ID doctor: ");
                    String idD = sc.nextLine().trim();
                    System.out.print("ID paciente: ");
                    String idP = sc.nextLine().trim();
                    Doctor   d = sistema.buscarDoctor(idD);
                    Paciente p = sistema.buscarPaciente(idP);
                    if (d == null || p == null) {
                        System.out.println("Doctor o paciente no encontrado.");
                    } else {
                        sistema.agregarCita(new Cita(id, fh, mot, d, p));
                        System.out.println("Cita creada.");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            case "3" -> {
                try {
                    System.out.print("ID cita a cancelar: ");
                    String id = sc.nextLine().trim();
                    System.out.println(sistema.eliminarCita(id)
                        ? "Cita cancelada." : "No encontrada.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        System.out.println();
    }
}
