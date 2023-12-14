package edu.hw10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RandomObjectGeneratorTest {
    private RandomObjectGenerator rog;

    @BeforeEach
    public void setUp() {
        rog = new RandomObjectGenerator();
    }

    @Test
    public void classGetNextObjectWithConstructor() throws Exception {
        MyClass myClass = rog.nextObject(MyClass.class);
        assertThat(myClass).isNotNull();
    }

    @Test
    public void classGetNextObjectWithFactoryMethod() throws Exception {
        MyClass myClass = rog.nextObject(MyClass.class, "create");
        assertThat(myClass).isNotNull();
    }

    @Test
    public void unsupportedClassTryGetNextObject() {
        assertThrows(IllegalArgumentException.class, () -> {
            rog.nextObject(UnsupportedClass.class);
        });
    }

    public static class MyClass {
        private final int value;
        private final String name;

        public MyClass(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public static MyClass create(int value, String name) {
            return new MyClass(value, name);
        }
    }

    public static class UnsupportedClass {
        private final MyClass myClass;

        public UnsupportedClass(MyClass myClass) {
            this.myClass = myClass;
        }
    }
}
