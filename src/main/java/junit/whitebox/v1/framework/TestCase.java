package junit.whitebox.v1.framework;

public abstract class TestCase {
    public final void run() {
        try {
            before();
            runTest();
            System.out.println("[OK] " + getClass().getSimpleName());
        } catch (Exception e) {
            System.out.println("[FAIL] " + getClass().getSimpleName() + ": " + e.getMessage());
        } finally {
            after();
        }
    }

    protected void before() {
    }

    protected void after() {
    }

    protected abstract void runTest() throws Exception;
}