public class Cita implements Persistible {
    private String id;
    private String fechaHora;
    private String motivo;
    private Doctor doctor;
    private Paciente paciente;

    public Cita(String id, String fechaHora, String motivo,
                Doctor doctor, Paciente paciente) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    public String getId()         { return id; }
    public Doctor getDoctor()     { return doctor; }
    public Paciente getPaciente() { return paciente; }

    // Solo guarda los IDs; al cargar se resuelven los objetos completos
    @Override
    public String toCSV() {
        return id + "," + fechaHora + "," + motivo + ","
             + doctor.getId() + "," + paciente.getId();
    }

    @Override
    public String toString() {
        return "ID: " + id + "  " + fechaHora + "  Motivo: " + motivo
             + "\n  Doctor: " + doctor.getNombre()
             + "  Paciente: " + paciente.getNombre();
    }
}
