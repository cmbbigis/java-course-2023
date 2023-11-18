package edu.hw6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public final class DiskMap implements Map<String, String> {
    private final Map<String, String> map;
    private final String filename;

    public DiskMap(String filename) {
        this.filename = filename;
        this.map = new HashMap<>();
        load();
    }

    private void load() {
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 2) {
                    String key = parts[0];
                    String value = parts[1];
                    map.put(key, value);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                writer.println(entry.getKey() + ":" + entry.getValue());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return map.get(key);
    }

    @Override
    public String put(String key, String value) {
        String result = map.put(key, value);
        save();
        return result;
    }

    @Override
    public String remove(Object key) {
        String result = map.remove(key);
        save();
        return result;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        map.putAll(m);
        save();
    }

    @Override
    public void clear() {
        map.clear();
        save();
    }

    @Override
    public @NotNull Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public @NotNull Collection<String> values() {
        return map.values();
    }

    @Override
    public @NotNull Set<Entry<String, String>> entrySet() {
        return map.entrySet();
    }
}
