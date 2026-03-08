
import java.util.Scanner;

class Menu {

    private static final String[] OPCIONES = {
        "1. Editar linea actual",
        "2. Borrar linea actual",
        "3. Copiar linea actual",
        "4. Pegar en linea actual",
        "5. Cambiar linea activa",
        "6. Intercambiar linea actual con otra",
        "7. Deshacer",
        "8. Rehacer",
        "9. Salir"
    };

    private final Editor editor;
    private boolean ejecutando;
    private final Scanner entrada;

    public Menu(Editor editor, Scanner entrada) {
        this.editor = editor;
        this.ejecutando = true;
        this.entrada = entrada;
    }

    public boolean estaEjecutando() {
        return ejecutando;
    }

    public void procesarAccion() {
        System.out.println("Opciones:");
        for (String opcionTexto : OPCIONES) {
            System.out.println(opcionTexto);
        }
        System.out.print("Seleccione una opción: ");
        int opcion = leerEntero();

        switch (opcion) {
            case 1 -> {
                System.out.print("Ingrese el nuevo texto: ");
                String nuevoTexto = entrada.nextLine();
                editor.editar(nuevoTexto);
            }
            case 2 ->
                editor.borrar();
            case 3 ->
                editor.copiar();
            case 4 ->
                editor.pegar();
            case 5 -> {
                System.out.print("Ingrese el número de la nueva línea activa: ");
                int nuevaLinea = leerEntero();
                editor.establecerLineaActiva(nuevaLinea);
            }
            case 6 -> {
                System.out.print("Ingrese el número de la línea con la que desea intercambiar: ");
                int lineaDestino = leerEntero();
                editor.intercambiarLineas(lineaDestino);
            }
            case 7 ->
                editor.deshacer();
            case 8 ->
                editor.rehacer();
            case 9 -> {
                System.out.println("Saliendo...");
                ejecutando = false;
            }
            default ->
                System.out.println("Opcion no valida.");
        }
    }

    private int leerEntero() {
        while (true) {
            String valor = entrada.nextLine();
            try {
                return Integer.parseInt(valor);
            } catch (NumberFormatException e) {
                System.out.print("Entrada invalida. Introduzca un numero: ");
            }
        }
    }
}
