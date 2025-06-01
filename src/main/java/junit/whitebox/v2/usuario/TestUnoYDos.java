package junit.whitebox.v2.usuario;

import junit.whitebox.v2.framework.TestCase;

import java.util.ArrayList;

import static junit.whitebox.v2.framework.MyAssert.assertEquals;

public class TestUnoYDos extends TestCase {
    private ArrayList<String> list;

    @Override
    protected void before() {
        list = new ArrayList<>();
    }

    public void testUno() throws Exception {
        list.add("Hola");
        list.add("Mundo");
        assertEquals(2, list.size());
    }

    public void testDos() throws Exception {
        list.add("Hola");
        assertEquals("Hello", list.get(0));
    }

    @Override
    protected void after() {
        list = new ArrayList<>();
    }
}
