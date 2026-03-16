import java.util.*;

public class ColeccionUsuario {

    // producto -> cantidad
    private Map<String, Integer> coleccion;
    // producto -> categoria (para poder mostrarla)
    private Map<String, String>  categorias;

    public ColeccionUsuario() {
        coleccion = new LinkedHashMap<>();
        categorias = new LinkedHashMap<>();
    }

    public void agregarProducto(String producto, String categoria) {
        coleccion.put(producto, coleccion.getOrDefault(producto, 0) + 1);
        categorias.put(producto, categoria);
    }

    // Operacion 3: mostrar coleccion tal cual
    public void mostrarColeccion() {
        if (coleccion.isEmpty()) {
            System.out.println("Tu coleccion esta vacia.");
            return;
        }
        System.out.printf("%-40s %-30s %s%n", "Producto", "Categoría", "Cantidad");
        System.out.println("-".repeat(80));
        for (Map.Entry<String, Integer> e : coleccion.entrySet()) {
            System.out.printf("%-40s %-30s %d%n",
                    e.getKey(), categorias.get(e.getKey()), e.getValue());
        }
    }

    // Operacion 4: mostrar coleccion ordenada por categoria
    public void mostrarColeccionOrdenadaPorCategoria() {
        if (coleccion.isEmpty()) {
            System.out.println("Tu coleccion esta vacia.");
            return;
        }

        // Ordenar por categoria y luego por nombre de producto
        List<Map.Entry<String, Integer>> lista = new ArrayList<>(coleccion.entrySet());
        lista.sort((a, b) -> {
            int cmp = categorias.get(a.getKey()).compareToIgnoreCase(categorias.get(b.getKey()));
            if (cmp != 0) return cmp;
            return a.getKey().compareToIgnoreCase(b.getKey());
        });

        System.out.printf("%-40s %-30s %s%n", "Producto", "Categoría", "Cantidad");
        System.out.println("-".repeat(80));
        for (Map.Entry<String, Integer> e : lista) {
            System.out.printf("%-40s %-30s %d%n",
                    e.getKey(), categorias.get(e.getKey()), e.getValue());
        }
    }
}
