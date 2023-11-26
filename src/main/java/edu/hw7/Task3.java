package edu.hw7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Task3 {
    private Task3() {
    }

    public static class Person {
        int id;
        String name;
        String address;
        String phoneNumber;

        public Person(int id, String name, String address, String phoneNumber) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.phoneNumber = phoneNumber;
        }
    }

    interface PersonDatabase {
        void add(Person person);

        void delete(int id);

        List<Person> findByName(String name);

        List<Person> findByAddress(String address);

        List<Person> findByPhone(String phone);
    }

    static class PersonDatabaseImpl implements PersonDatabase {
        private final Map<Integer, Person> peopleById = new HashMap<>();
        private final Map<String, List<Person>> peopleByName = new HashMap<>();
        private final Map<String, List<Person>> peopleByAddress = new HashMap<>();
        private final Map<String, List<Person>> peopleByPhone = new HashMap<>();

        @Override
        public synchronized void add(Person person) {
            peopleById.put(person.id, person);
            peopleByName.computeIfAbsent(person.name, k -> new ArrayList<>()).add(person);
            peopleByAddress.computeIfAbsent(person.address, k -> new ArrayList<>()).add(person);
            peopleByPhone.computeIfAbsent(person.phoneNumber, k -> new ArrayList<>()).add(person);
        }

        @Override
        public synchronized void delete(int id) {
            Person person = peopleById.remove(id);
            if (person != null) {
                peopleByName.get(person.name).remove(person);
                peopleByAddress.get(person.address).remove(person);
                peopleByPhone.get(person.phoneNumber).remove(person);
            }
        }

        @Override
        public synchronized List<Person> findByName(String name) {
            return new ArrayList<>(peopleByName.getOrDefault(name, Collections.emptyList()));
        }

        @Override
        public synchronized List<Person> findByAddress(String address) {
            return new ArrayList<>(peopleByAddress.getOrDefault(address, Collections.emptyList()));
        }

        @Override
        public synchronized List<Person> findByPhone(String phone) {
            return new ArrayList<>(peopleByPhone.getOrDefault(phone, Collections.emptyList()));
        }
    }
}
