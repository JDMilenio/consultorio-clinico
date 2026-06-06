import java.io.*;
import java.util.*;

public class Sistema {
    private List<Doctor>   doctores  = new ArrayList<>();
    private List<Paciente> pacientes = new ArrayList<>();
    private List<Cita>     citas     = new ArrayList<>();
    private List<Admin>    admins    = new ArrayList<>();

    private static final String DOCTORES  = "db/doctores.csv";
    private static final String PACIENTES = "db/pacientes.csv";
    private static final String CITAS     = "db/citas.csv";
    private static final String ADMINS    = "db/admins.csv";

    public void cargarDatos() throws IOException {
        for (String l : CSVManager.cargar(DOCTORES))  doctores.add(Doctor.fromCSV(l));
        for (String l : CSVManager.cargar(PACIENTES)) pacientes.add(Paciente.fromCSV(l));
        for (String l : CSVManager.cargar(ADMINS))    admins.add(Admin.fromCSV(l));

        // Las citas se cargan después para que doctor y paciente ya existan
        for (String l : CSVManager.cargar(CITAS)) {
            try {
                String[] p = l.split(",", 5);
                Doctor   d  = buscarDoctor(p[3]);
                Paciente pa = buscarPaciente(p[4]);
                if (d != null && pa != null)
                    citas.add(new Cita(p[0], p[1], p[2], d, pa));
            } catch (Exception ignored) {}
        }

        // Crea admin por defecto si el archivo está vacío
        if (admins.isEmpty()) {
            admins.add(new Admin("admin", "Administrador", "admin123"));
            guardarAdmins();
        }
    }

    public Admin autenticar(String id, String pwd) {
        for (Admin a : admins)
            if (a.getId().equals(id) && a.verificarPassword(pwd)) return a;
        return null;
    }

    public void agregarDoctor(Doctor d) throws IOException {
        if (buscarDoctor(d.getId()) != null)
            throw new IllegalArgumentException("ID ya existe");
        doctores.add(d);
        CSVManager.guardar(DOCTORES, doctores);
    }

    public boolean eliminarDoctor(String id) throws IOException {
        boolean ok = doctores.removeIf(d -> d.getId().equals(id));
        if (ok) CSVManager.guardar(DOCTORES, doctores);
        return ok;
    }

    public Doctor buscarDoctor(String id) {
        for (Doctor d : doctores) if (d.getId().equals(id)) return d;
        return null;
    }

    public List<Doctor> getDoctores() { return doctores; }

    public void agregarPaciente(Paciente p) throws IOException {
        if (buscarPaciente(p.getId()) != null)
            throw new IllegalArgumentException("ID ya existe");
        pacientes.add(p);
        CSVManager.guardar(PACIENTES, pacientes);
    }

    public boolean eliminarPaciente(String id) throws IOException {
        boolean ok = pacientes.removeIf(p -> p.getId().equals(id));
        if (ok) CSVManager.guardar(PACIENTES, pacientes);
        return ok;
    }

    public Paciente buscarPaciente(String id) {
        for (Paciente p : pacientes) if (p.getId().equals(id)) return p;
        return null;
    }

    public List<Paciente> getPacientes() { return pacientes; }

    public void agregarCita(Cita c) throws IOException {
        if (buscarCita(c.getId()) != null)
            throw new IllegalArgumentException("ID ya existe");
        citas.add(c);
        CSVManager.guardar(CITAS, citas);
    }

    public boolean eliminarCita(String id) throws IOException {
        boolean ok = citas.removeIf(c -> c.getId().equals(id));
        if (ok) CSVManager.guardar(CITAS, citas);
        return ok;
    }

    public Cita buscarCita(String id) {
        for (Cita c : citas) if (c.getId().equals(id)) return c;
        return null;
    }

    public List<Cita> getCitas() { return citas; }

    private void guardarAdmins() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ADMINS))) {
            for (Admin a : admins) pw.println(a.toCSV());
        }
    }
}
