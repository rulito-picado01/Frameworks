package junit.whitebox.v1.usuario;

import junit.whitebox.v1.framework.TestCase;

import java.util.ArrayList;

import static junit.whitebox.v1.framework.MyAssert.assertEquals;

public class TestUno extends TestCase {
    private ArrayList<String> list;

    @Override
    protected void before() {
        list = new ArrayList<>();
    }

    @Override
    protected void runTest() throws Exception {
        list.add("Hola");
        list.add("Mundo");
        assertEquals(2, list.size());
    }

    @Override
    protected void after() {
        list = new ArrayList<>();
    }

}
