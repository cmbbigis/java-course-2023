package edu.hw10;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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
                if (method.isAnnotationPresent(Cache.class)) {
                    String key = method.getName() + Arrays.toString(args);
                    if (cache.containsKey(key)) {
                        return cache.get(key);
                    } else {
                        Object result = method.invoke(obj, args);
                        cache.put(key, result);
                        return result;
                    }
                } else {
                    return method.invoke(obj, args);
                }
            }
        }));
    }
}
