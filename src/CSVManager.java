import java.io.*;
import java.util.*;

public class CSVManager {

    // Crea el archivo si no existe para evitar errores en la primera ejecución
    public static List<String> cargar(String ruta) throws IOException {
        List<String> lineas = new ArrayList<>();
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            archivo.getParentFile().mkdirs();
            archivo.createNewFile();
            return lineas;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null)
                if (!linea.trim().isEmpty()) lineas.add(linea.trim());
        }
        return lineas;
    }

    // Sobreescribe el archivo completo en cada guardado
    public static <T extends Persistible> void guardar(
            String ruta, List<T> lista) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ruta))) {
            for (T item : lista) pw.println(item.toCSV());
        }
    }
}
