package junit.whitebox.v1.framework;

public class TestRunner {
    public static void executeAll(TestCase[] tests) {
        for (TestCase test : tests) {
            test.run();
        }
    }
}
