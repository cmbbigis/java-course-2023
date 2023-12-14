package edu.hw6;

import org.junit.jupiter.api.*;
import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class DiskMapTest {
    private DiskMap diskMap;
    private final String filename = "test.txt";

    @BeforeEach
    public void setUp() {
        diskMap = new DiskMap(filename);
    }

    @AfterEach
    public void tearDown() {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void pairPut() {
        diskMap.put("ключ", "значение");
        assertThat(diskMap.get("ключ")).isEqualTo("значение");
    }

    @Test
    public void keyRemove() {
        diskMap.put("ключ", "значение");
        diskMap.remove("ключ");
        assertThat(diskMap.get("ключ")).isNull();
    }

    @Test
    public void keyCheckContainsKey() {
        diskMap.put("ключ", "значение");
        assertThat(diskMap.containsKey("ключ")).isTrue();
        assertThat(diskMap.containsKey("другой ключ")).isFalse();
    }

    @Test
    public void valueCheckContainsValue() {
        diskMap.put("ключ", "значение");
        assertThat(diskMap.containsValue("значение")).isTrue();
        assertThat(diskMap.containsValue("другое значение")).isFalse();
    }

    @Test
    public void pairsPutAll() {
        Map<String, String> anotherMap = new HashMap<>();
        anotherMap.put("ключ1", "значение1");
        anotherMap.put("ключ2", "значение2");

        diskMap.putAll(anotherMap);

        assertThat(diskMap.get("ключ1")).isEqualTo("значение1");
        assertThat(diskMap.get("ключ2")).isEqualTo("значение2");
    }

    @Test
    public void persistence() {
        diskMap.put("ключ", "значение");
        diskMap = new DiskMap(filename);
        assertThat(diskMap.get("ключ")).isEqualTo("значение");
    }

    @Test
    public void size() {
        diskMap.put("ключ1", "значение1");
        diskMap.put("ключ2", "значение2");
        assertThat(diskMap.size()).isEqualTo(2);
    }

    @Test
    public void isEmpty() {
        assertThat(diskMap.isEmpty()).isTrue();
        diskMap.put("ключ", "значение");
        assertThat(diskMap.isEmpty()).isFalse();
    }

    @Test
    public void clear() {
        diskMap.put("ключ", "значение");
        diskMap.clear();
        assertThat(diskMap.isEmpty()).isTrue();
    }

    @Test
    public void keySet() {
        diskMap.put("ключ1", "значение1");
        diskMap.put("ключ2", "значение2");
        Set<String> keys = diskMap.keySet();
        assertThat(keys.size()).isEqualTo(2);
        assertThat(keys.contains("ключ1")).isTrue();
        assertThat(keys.contains("ключ2")).isTrue();
    }

    @Test
    public void values() {
        diskMap.put("ключ1", "значение1");
        diskMap.put("ключ2", "значение2");
        Collection<String> values = diskMap.values();
        assertThat(values.size()).isEqualTo(2);
        assertThat(values.contains("значение1")).isTrue();
        assertThat(values.contains("значение2")).isTrue();
    }

    @Test
    public void entrySet() {
        diskMap.put("ключ1", "значение1");
        diskMap.put("ключ2", "значение2");
        Set<Map.Entry<String, String>> entries = diskMap.entrySet();
        assertThat(entries.size()).isEqualTo(2);
    }
}
