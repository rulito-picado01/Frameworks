package junit.whitebox.v2.framework;

public class TestRunner {
    public static void executeAll(TestCase[] tests) {
        for (TestCase test : tests) {
            test.run();
        }
    }
}
