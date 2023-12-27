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
        MyClassWithoutAnnotations myClass = rog.nextObject(MyClassWithoutAnnotations.class);
        assertThat(myClass).isNotNull();
    }

    @Test
    public void classGetNextObjectWithFactoryMethod() throws Exception {
        MyClassWithoutAnnotations myClass = rog.nextObject(MyClassWithoutAnnotations.class, "create");
        assertThat(myClass).isNotNull();
    }

    @Test
    public void myRecordCheckThatAnnotationsAreTakenIntoAccount() throws Exception {
        MyRecord myClass = rog.nextObject(MyRecord.class);
        assertThat(myClass.name).isNotNull();
        assertThat(myClass.intValue).isEqualTo(1);
        assertThat(myClass.longValue).isEqualTo(1);
        assertThat(myClass.doubleValue).isGreaterThan(1).isLessThan(2);
    }

    @Test
    public void unsupportedClassTryGetNextObject() {
        assertThrows(IllegalArgumentException.class, () -> {
            rog.nextObject(Character.class);
        });
    }

    public static class MyClassWithoutAnnotations {
        private final int value;
        private final String name;

        public MyClassWithoutAnnotations(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public static MyClassWithoutAnnotations create(int value, String name) {
            return new MyClassWithoutAnnotations(value, name);
        }
    }

    public record MyRecord(int intValue, long longValue, double doubleValue, String name) {
            public MyRecord(
                @Annotations.Max(2) @Annotations.Min(1) int intValue,
                @Annotations.Max(2) @Annotations.Min(1) long longValue,
                @Annotations.Max(2) @Annotations.Min(1) double doubleValue,
                @Annotations.NotNull String name
            ) {
                this.intValue = intValue;
                this.longValue = longValue;
                this.doubleValue = doubleValue;
                this.name = name;
            }
        }
}
