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
        var notNullAnnotation = parameter.getAnnotation(Annotations.NotNull.class);
        var minAnnotation = parameter.getAnnotation(Annotations.Min.class);
        var maxAnnotation = parameter.getAnnotation(Annotations.Max.class);
        if (type == int.class) {
            var max = Integer.MAX_VALUE;
            var min = Integer.MIN_VALUE;
            if (maxAnnotation != null) {
                max = maxAnnotation.value();
            }
            if (minAnnotation != null) {
                min = minAnnotation.value();
            }
            return random.nextInt(min, max);
        } else if (type == String.class) {
            return notNullAnnotation == null ? null : UUID.randomUUID().toString();
        } else if (type == Boolean.class) {
            return random.nextBoolean();
        } else {
            try {
                return nextObject(type);
            } catch (Exception e) {
                throw new IllegalArgumentException("Unsupported type: " + type, e);
            }
        }
    }
}
