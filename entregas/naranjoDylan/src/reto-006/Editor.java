
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

class Editor implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int HISTORIAL_MAXIMO = 100;

    private final String[] documento;
    private int lineaActiva;
    private String portapapeles;
    
    private final Deque<String[]> pilaDeshacer;
    private final Deque<String[]> pilaRehacer;

    public Editor(int numLineas) {
        if (numLineas < 1) {
            throw new IllegalArgumentException("El numero de lineas debe ser mayor o igual que 1.");
        }

        this.documento = new String[numLineas];
        for (int i = 0; i < numLineas; i++) {
            documento[i] = "";
        }
        this.lineaActiva = 0;
        this.portapapeles = "";
        this.pilaDeshacer = new ArrayDeque<>();
        this.pilaRehacer = new ArrayDeque<>();
    }

    public void imprimir() {
        System.out.println();
        imprimirLineaHorizontal();
        for (int linea = 0; linea < this.documento.length; linea++) {
            System.out.println(linea + separador(linea) + documento[linea]);
        }
        imprimirLineaHorizontal();
    }

    public void establecerLineaActiva(int nuevaLinea) {
        if (esLineaValida(nuevaLinea)) {
            lineaActiva = nuevaLinea;
        } else {
            System.out.println("Número de línea no válido.");
        }
    }

    public int getLineaActiva() {
        return lineaActiva;
    }

    public void editar(String nuevoTexto) {
        modificarLineaActual(nuevoTexto);
    }

    public void borrar() {
        modificarLineaActual("");
    }

    public void copiar() {
        portapapeles = documento[lineaActiva];
    }

    public void pegar() {
        if (!portapapeles.isEmpty()) {
            modificarLineaActual(portapapeles);
            System.out.println(
                String.format("Pegado en línea %d", lineaActiva)
            );
        } else {
            System.out.println("El portapapeles está vacío.");
        }
    }
    
    private void modificarLineaActual(String nuevoContenido) {
        guardarEstadoPrevio();
        documento[lineaActiva] = nuevoContenido;
    }

    public void intercambiarLineas(int lineaDestino) {
        if (esLineaValida(lineaDestino)) {
            guardarEstadoPrevio();
            String temporalLinea = documento[lineaDestino];
            documento[lineaDestino] = documento[lineaActiva];
            documento[lineaActiva] = temporalLinea;
        } else {
            System.out.println("Numero de linea no valido.");
        }
    }

    public void deshacer() {
        if (!pilaDeshacer.isEmpty()) {
            pilaRehacer.push(clonarDocumento());
            restaurarDocumento(pilaDeshacer.pop());
            System.out.println("Deshecho.");
        } else {
            System.out.println("No hay más acciones para deshacer.");
        }
    }

    public void rehacer() {
        if (!pilaRehacer.isEmpty()) {
            pilaDeshacer.push(clonarDocumento());
            restaurarDocumento(pilaRehacer.pop());
            System.out.println("Rehecho.");
        } else {
            System.out.println("No hay más acciones para rehacer.");
        }
    }

    private boolean esLineaValida(int linea) {
        return linea >= 0 && linea < documento.length;
    }

    private void guardarEstadoPrevio() {
        pilaDeshacer.push(clonarDocumento());
        while (pilaDeshacer.size() > HISTORIAL_MAXIMO) {
            pilaDeshacer.removeLast();
        }
        pilaRehacer.clear();
    }

    private String[] clonarDocumento() {
        return documento.clone();
    }

    private void restaurarDocumento(String[] snapshot) {
        for (int i = 0; i < documento.length; i++) {
            documento[i] = snapshot[i];
        }
    }

    private String separador(int linea) {
        return linea == lineaActiva ? ":*| " : ": | ";
    }

    private void imprimirLineaHorizontal() {
        System.out.println("-".repeat(50));
    }
}
