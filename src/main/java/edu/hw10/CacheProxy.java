package edu.hw10;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy {
    private CacheProxy() {
    }

    public static <T> T create(T obj, Class<T> clazz) {
        return clazz.cast(Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[] {clazz},
            new InvocationHandler() {
            private final Map<String, Object> cache = new HashMap<>();

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                var annotation = method.getAnnotation(Cache.class);
                if (annotation == null) {
                    return method.invoke(obj, args);
                }
                var cached = cache.get(String.valueOf(Arrays.hashCode(args)));
                if (cached != null) {
                    return cached;
                }
                var methodResult = method.invoke(obj, args);
                var hash = Arrays.hashCode(args);
                cache.put(String.valueOf(hash), methodResult);
                if (annotation.persist()) {
                    File file = Path.of(".").resolve(String.valueOf(hash)).toFile();
                    file.createNewFile();
                    try (var writer = new FileWriter(file)) {
                        for (var elem : args) {
                            writer.write("Arg " + elem.toString() + "\n");
                        }
                        writer.write("Result value " + methodResult.toString());
                        writer.flush();
                    }
                }
                return methodResult;
            }
        }));
    }
}
