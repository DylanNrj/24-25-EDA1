
import java.io.File;
import java.util.Scanner;

class Edlin {

    private static final String DIRECTORIO_PERSISTENCIA = "guardado";
    private static final String EXTENSION = ".edin";

    public static void main(String[] args) {
        boolean usarPersistencia = !contieneFlag(args, "--no-persist");
        
        prepararCarpetaGuardado(DIRECTORIO_PERSISTENCIA);

        Scanner entrada = new Scanner(System.in);
        Editor editor = null;
        
        if (usarPersistencia) {
            editor = cargarEditorSiCorresponde(entrada, DIRECTORIO_PERSISTENCIA);
        }

        if (editor == null) {
            System.out.println("[INFO] Creando un nuevo editor...");
            int numLineas = pedirNumeroLineas(entrada);
            editor = new Editor(numLineas);
        }

        Menu menu = new Menu(editor, entrada);

        do {
            editor.imprimir();
            menu.procesarAccion();
        } while (menu.estaEjecutando());

        if (usarPersistencia) {
            System.out.print("[ACCION] ¿Desea guardar el editor antes de salir? (S/N): ");
            if (entrada.nextLine().equalsIgnoreCase("S")) {
                System.out.print("Ingrese el nombre del archivo (sin extension): ");
                String nombreArchivo = entrada.nextLine();
                nombreArchivo = asegurarExtension(nombreArchivo, EXTENSION);
                SerializadorEditor.serializar(editor, DIRECTORIO_PERSISTENCIA + File.separator + nombreArchivo);
            }
        }

        entrada.close();
    }
    
    private static boolean contieneFlag(String[] args, String flag) {
        for (String arg : args) {
            if (arg.equals(flag)) {
                return true;
            }
        }
        return false;
    }

    private static Editor cargarEditorSiCorresponde(Scanner entrada, String carpetaGuardado) {
        System.out.print("[ACCION] ¿Desea cargar un editor existente? (S/N): ");
        if (!entrada.nextLine().equalsIgnoreCase("S")) {
            return null;
        }

        System.out.print("Ingrese el nombre del archivo (sin extension): ");
        String nombreArchivo = asegurarExtension(entrada.nextLine(), EXTENSION);
        return SerializadorEditor.deserializar(carpetaGuardado + File.separator + nombreArchivo);
    }

    private static int pedirNumeroLineas(Scanner entrada) {
        int numLineas = 0;

        do {
            System.out.print("Ingrese el numero de lineas para el bloc de notas (minimo 1): ");
            String valor = entrada.nextLine();
            try {
                numLineas = Integer.parseInt(valor);
            } catch (NumberFormatException e) {
                numLineas = 0;
                System.out.println("Debe introducir un numero entero.");
            }
        } while (numLineas < 1);

        return numLineas;
    }

    private static void prepararCarpetaGuardado(String carpeta) {
        File directorio = new File(carpeta);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("[INFO] Carpeta de guardado creada: " + carpeta);
            } else {
                System.out.println("No se pudo crear la carpeta de guardado: " + carpeta);
            }
        }
    }

    private static String asegurarExtension(String nombreArchivo, String extension) {
        if (!nombreArchivo.endsWith(extension)) {
            nombreArchivo += extension;
        }
        return nombreArchivo;
    }
}
