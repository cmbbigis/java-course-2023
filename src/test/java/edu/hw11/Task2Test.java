package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    void testToString() throws IllegalAccessException, InstantiationException {
        TypeDescription typeDescription = TypePool.Default.ofSystemLoader()
            .describe("edu.hw11.ArithmeticUtils")
            .resolve();
        Class<?> dynamicType = new ByteBuddy()
            .redefine(typeDescription, ClassFileLocator.ForClassLoader.ofSystemLoader())
            .method(ElementMatchers.named("sum"))
            .intercept(FixedValue.value(1000))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();

        ArithmeticUtils instance = (ArithmeticUtils) dynamicType.newInstance();

        assertThat(instance.sum(1,1)).isEqualTo(1000);
    }
}
