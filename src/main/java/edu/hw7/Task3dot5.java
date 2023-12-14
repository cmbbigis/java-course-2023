package edu.hw7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class Task3dot5 {
    private Task3dot5() {
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
        private final ReadWriteLock lock = new ReentrantReadWriteLock();

        @Override
        public void add(Person person) {
            lock.writeLock().lock();
            try {
                peopleById.put(person.id, person);
                peopleByName.computeIfAbsent(person.name, k -> new ArrayList<>()).add(person);
                peopleByAddress.computeIfAbsent(person.address, k -> new ArrayList<>()).add(person);
                peopleByPhone.computeIfAbsent(person.phoneNumber, k -> new ArrayList<>()).add(person);
            } finally {
                lock.writeLock().unlock();
            }
        }

        @Override
        public void delete(int id) {
            lock.writeLock().lock();
            try {
                Person person = peopleById.remove(id);
                if (person != null) {
                    peopleByName.get(person.name).remove(person);
                    peopleByAddress.get(person.address).remove(person);
                    peopleByPhone.get(person.phoneNumber).remove(person);
                }
            } finally {
                lock.writeLock().unlock();
            }
        }

        @Override
        public List<Person> findByName(String name) {
            lock.readLock().lock();
            try {
                return new ArrayList<>(peopleByName.getOrDefault(name, Collections.emptyList()));
            } finally {
                lock.readLock().unlock();
            }
        }

        @Override
        public List<Person> findByAddress(String address) {
            lock.readLock().lock();
            try {
                return new ArrayList<>(peopleByAddress.getOrDefault(address, Collections.emptyList()));
            } finally {
                lock.readLock().unlock();
            }
        }

        @Override
        public List<Person> findByPhone(String phone) {
            lock.readLock().lock();
            try {
                return new ArrayList<>(peopleByPhone.getOrDefault(phone, Collections.emptyList()));
            } finally {
                lock.readLock().unlock();
            }
        }
    }
}
