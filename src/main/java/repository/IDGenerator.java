package repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class IDGenerator {
    private static IDGenerator instance;
    private final HashMap<String, AtomicInteger> map = new HashMap();

    public static IDGenerator getInstance() {
        if (instance == null)
            instance = new IDGenerator();

        return instance;
    }

    public void registerTopFor(String name, Integer value) {
        map.put(name, new AtomicInteger(value));
    }

    public int getIdFor(String name) {
        if (!map.containsKey(name)) {
            map.put(name, new AtomicInteger(0));
        }
        return map.get(name).getAndIncrement();
    }

}
