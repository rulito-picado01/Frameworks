package junit.blackbox.framework;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    private Method beforeMethod;
    private Method afterMethod;
    private List<Method> testMethods;

    public TestRunner() {
        testMethods = new ArrayList<>();
    }

    public void runTests(Class<?>... testClasses) {
        for (Class<?> clazz : testClasses) {
            var instance = createInstanceOf(clazz);
            distributeMethodsIntoLists(clazz);
            for (Method testMethod : testMethods) {
                try {
                    executeBeforeIfAny(instance);
                    testMethod.invoke(instance);
                    System.out.println("[OK] " + clazz.getSimpleName() + "." + testMethod.getName());
                } catch (Exception e) {
                    Throwable cause = e.getCause();
                    System.out.println("[FAIL] " + clazz.getSimpleName() + "." + testMethod.getName()
                            + ": " + (cause != null ? cause.getMessage() : e.getMessage()));
                } finally {
                    executeAfterIfAny(instance);
                }
            }
        }
    }

    private void executeAfterIfAny(Object instance) {
        try {
            if (afterMethod != null) {
                afterMethod.invoke(instance);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error invoking after method: " + e.getMessage(), e);
        }
    }

    private void executeBeforeIfAny(Object instance) throws IllegalAccessException, InvocationTargetException {
        if (beforeMethod != null) {
            beforeMethod.invoke(instance);
        }
    }

    private void distributeMethodsIntoLists(Class<?> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Before.class)) {
                assertOnlyOneBeforeMethod(clazz);
                beforeMethod = method;
            } else if (method.isAnnotationPresent(After.class)) {
                assertOnlyOneAfterMethod(clazz);
                afterMethod = method;
            } else if (method.isAnnotationPresent(TestUnitario.class)) {
                testMethods.add(method);
            }
        }
    }

    private void assertOnlyOneAfterMethod(Class<?> clazz) {
        if (afterMethod != null) {
            throw new RuntimeException("Multiple @After methods found in " + clazz.getSimpleName());
        }
    }

    private void assertOnlyOneBeforeMethod(Class<?> clazz) {
        if (beforeMethod != null) {
            throw new RuntimeException("Multiple @Before methods found in " + clazz.getSimpleName());
        }
    }

    @NotNull
    private Object createInstanceOf(Class<?> clazz) {
        Object instance;
        try {
            instance = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return instance;
    }
}