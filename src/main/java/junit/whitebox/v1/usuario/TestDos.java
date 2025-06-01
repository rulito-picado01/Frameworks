package junit.whitebox.v1.usuario;

import junit.whitebox.v1.framework.TestCase;

import java.util.ArrayList;

import static junit.whitebox.v1.framework.MyAssert.assertEquals;

public class TestDos extends TestCase {
    private ArrayList<String> list;

    @Override
    protected void before() {
        list = new ArrayList<>();
    }

    @Override
    protected void runTest() throws Exception {
        list.add("Hola");
        assertEquals("Hello", list.get(0));
    }

    @Override
    protected void after() {
        list = new ArrayList<>();
    }
}
