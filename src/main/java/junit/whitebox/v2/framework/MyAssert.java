package junit.whitebox.v2.framework;

public class MyAssert {
    public static void assertEquals(Object valorEsperado, Object valor) {
        if (valorEsperado == null && valor == null) {
            return;
        }
        if (!valorEsperado.equals(valor)) {
            throw new RuntimeException("Esperado: " + valorEsperado + ", pero fue: " + valor);
        }
    }
}
