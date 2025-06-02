package junit.blackbox.usuario;

import junit.blackbox.framework.TestRunner;

public class Main {
    public static void main(String[] args) {
        var tests = new Class[]{
                TestUnoYDos.class
        };
        new TestRunner().runTests(tests);
    }
}
