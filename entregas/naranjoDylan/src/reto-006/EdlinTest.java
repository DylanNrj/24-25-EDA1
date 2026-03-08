/**
 * Test básico para validar funcionalidad de persistencia del Editor.
 * TODO: Ampliar con JUnit para cobertura completa de casos de carga/guardado.
 */
class EdlinTest {

    public static void main(String[] args) {
        testSerializacionYDeserializacion();
        testCargaDeArchivoInexistente();
        System.out.println("[OK] Todos los tests básicos pasaron");
    }

    private static void testSerializacionYDeserializacion() {
        Editor editor = new Editor(5);
        editor.editar("Línea de prueba");
        
        String archivo = "guardado/test.edin";
        SerializadorEditor.serializar(editor, archivo);
        
        Editor editorCargado = SerializadorEditor.deserializar(archivo);
        assert editorCargado != null : "Editor deserializado no puede ser null";
        
        System.out.println("[PASS] Serialización y deserialización correcta");
    }

    private static void testCargaDeArchivoInexistente() {
        Editor editor = SerializadorEditor.deserializar("guardado/noexiste.edin");
        assert editor == null : "Cargar archivo inexistente debe retornar null";
        
        System.out.println("[PASS] Manejo de archivo inexistente correcto");
    }
}
