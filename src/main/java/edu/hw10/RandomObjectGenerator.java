package edu.hw10;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Random;
import java.util.UUID;

public class RandomObjectGenerator {
    private final Random random = new Random();

    public <T> T nextObject(Class<T> clazz, String methodName) throws Exception {
        Method method = null;
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.getName().equals(methodName)) {
                method = m;
                break;
            }
        }
        if (method == null) {
            throw new NoSuchMethodException("Method " + methodName + " not found in class " + clazz.getName());
        }
        return clazz.cast(method.invoke(null, generateParametersForMethod(method)));
    }

    public <T> T nextObject(Class<T> clazz) throws Exception {
        Constructor<T> constructor = (Constructor<T>) clazz.getDeclaredConstructors()[0];
        return constructor.newInstance(generateParametersForConstructor(constructor));
    }

    private Object[] generateParametersForMethod(Method method) {
        return generateParameters(method.getParameters());
    }

    private Object[] generateParametersForConstructor(Constructor<?> constructor) {
        return generateParameters(constructor.getParameters());
    }

    private Object[] generateParameters(Parameter[] parameters) {
        Object[] values = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            values[i] = generateValue(parameters[i]);
        }
        return values;
    }

    private Object generateValue(Parameter parameter) {
        Class<?> type = parameter.getType();
        if (type == int.class) {
            return random.nextInt();
        } else if (type == double.class) {
            return random.nextDouble();
        } else if (type == String.class) {
            return UUID.randomUUID().toString();
        } else {
            throw new IllegalArgumentException("Unsupported type: " + type);
        }
    }
}
