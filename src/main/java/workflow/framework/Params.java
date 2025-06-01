package workflow.framework;

import java.util.HashMap;
import java.util.Map;

public class Params {
    private Map<String, Object> data = new HashMap<>();

    public Params(Map<String, Object> initialData) {
        this.data.putAll(initialData);
    }

    public static Params empty() {
        return new Params(new HashMap<>());
    }

    public void set(String key, Object value) {
        data.put(key, value);
    }

    public Object get(String key) {
        return data.get(key);
    }
}
