package junit.whitebox.v2.usuario;

import junit.whitebox.v2.framework.TestCase;
import junit.whitebox.v2.framework.TestRunner;

public class Main {
    public static void main(String[] args) {
        var tests = new TestCase[]{
                new TestUnoYDos()
        };
        TestRunner.executeAll(tests);
    }
}
