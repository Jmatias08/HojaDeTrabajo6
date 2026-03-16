import java.io.*;
import java.util.*;

public class Inventario {

    private Map<String, String> catalogo; // producto -> categoria

    public Inventario(MapFactory factory) {
        catalogo = factory.crearMapa();
    }

    public void cargarArchivo(String ruta) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(ruta), "UTF-8"))) {

            String linea;
            boolean primera = true;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                // saltar encabezado
                if (primera) {
                    primera = false;
                    if (linea.toLowerCase().contains("categoría") || linea.toLowerCase().contains("categoria")) {
                        continue;
                    }
                }

                String[] partes = linea.split("\\|");
                if (partes.length >= 2) {
                    String categoria = partes[0].trim();
                    String producto  = partes[1].trim();
                    catalogo.put(producto, categoria);
                }
            }

            System.out.println("Inventario cargado: " + catalogo.size() + " productos.");

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public Map<String, String> getCatalogo() {
        return catalogo;
    }

    public boolean existeProducto(String producto) {
        return catalogo.containsKey(producto);
    }

    public String getCategoriaDeProducto(String producto) {
        return catalogo.get(producto);
    }
}
