import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in, "UTF-8");

        // --- Seleccion de implementacion (Factory) ---
        System.out.println("=== Tienda Online ===");
        System.out.println("Seleccione la implementacion de Map:");
        System.out.println("  1. HashMap");
        System.out.println("  2. TreeMap");
        System.out.println("  3. LinkedHashMap");
        System.out.print("Opcion: ");

        int opcionMapa = 0;
        try {
            opcionMapa = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Opcion no valida. Se usara HashMap por defecto.");
            opcionMapa = 1;
        }

        MapFactory factory;
        switch (opcionMapa) {
            case 2:  factory = new TreeMapFactory();       break;
            case 3:  factory = new LinkedHashMapFactory(); break;
            default: factory = new HashMapFactory();       break;
        }

        // --- Cargar inventario ---
        Inventario inventario = new Inventario(factory);
        inventario.cargarArchivo("inventario.txt");

        ColeccionUsuario coleccion = new ColeccionUsuario();

        // --- Menu principal ---
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Agregar producto a mi coleccion");
            System.out.println("2. Ver categoria de un producto");
            System.out.println("3. Ver mi coleccion");
            System.out.println("4. Ver mi coleccion ordenada por categoria");
            System.out.println("5. Ver todo el inventario");
            System.out.println("6. Ver inventario ordenado por categoria");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");

            String input = sc.nextLine().trim();

            switch (input) {
                case "1":
                    agregarProducto(sc, inventario, coleccion);
                    break;
                case "2":
                    buscarCategoria(sc, inventario);
                    break;
                case "3":
                    coleccion.mostrarColeccion();
                    break;
                case "4":
                    coleccion.mostrarColeccionOrdenadaPorCategoria();
                    break;
                case "5":
                    mostrarInventario(inventario);
                    break;
                case "6":
                    mostrarInventarioOrdenado(inventario);
                    break;
                case "0":
                    salir = true;
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opcion no reconocida.");
            }
        }

        sc.close();
    }

    // Operacion 1
    private static void agregarProducto(Scanner sc, Inventario inventario, ColeccionUsuario coleccion) {
        System.out.print("Nombre del producto a agregar: ");
        String nombre = sc.nextLine().trim();

        if (!inventario.existeProducto(nombre)) {
            System.out.println("Error: el producto '" + nombre + "' no existe en el inventario.");
        } else {
            String categoria = inventario.getCategoriaDeProducto(nombre);
            coleccion.agregarProducto(nombre, categoria);
            System.out.println("'" + nombre + "' agregado a tu coleccion. (Categoria: " + categoria + ")");
        }
    }

    // Operacion 2
    private static void buscarCategoria(Scanner sc, Inventario inventario) {
        System.out.print("Nombre del producto: ");
        String nombre = sc.nextLine().trim();

        String categoria = inventario.getCategoriaDeProducto(nombre);
        if (categoria == null) {
            System.out.println("Producto no encontrado en el inventario.");
        } else {
            System.out.println("Categoria de '" + nombre + "': " + categoria);
        }
    }

    // Operacion 5
    private static void mostrarInventario(Inventario inventario) {
        Map<String, String> catalogo = inventario.getCatalogo();
        System.out.printf("%-40s %s%n", "Producto", "Categoría");
        System.out.println("-".repeat(65));
        for (Map.Entry<String, String> e : catalogo.entrySet()) {
            System.out.printf("%-40s %s%n", e.getKey(), e.getValue());
        }
        System.out.println("Total de productos: " + catalogo.size());
    }

    // Operacion 6
    private static void mostrarInventarioOrdenado(Inventario inventario) {
        Map<String, String> catalogo = inventario.getCatalogo();

        List<Map.Entry<String, String>> lista = new ArrayList<>(catalogo.entrySet());
        lista.sort((a, b) -> {
            int cmp = a.getValue().compareToIgnoreCase(b.getValue());
            if (cmp != 0) return cmp;
            return a.getKey().compareToIgnoreCase(b.getKey());
        });

        System.out.printf("%-40s %s%n", "Producto", "Categoría");
        System.out.println("-".repeat(65));
        for (Map.Entry<String, String> e : lista) {
            System.out.printf("%-40s %s%n", e.getKey(), e.getValue());
        }
        System.out.println("Total de productos: " + lista.size());
    }
}
