package edu.hw7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3dot5Test {
    private Task3dot5.PersonDatabaseImpl db;
    private Task3dot5.Person person1;
    private Task3dot5.Person person2;

    @BeforeEach
    public void setup() {
        db = new Task3dot5.PersonDatabaseImpl();
        person1 = new Task3dot5.Person(1, "Alice", "123 Street", "111-1111");
        person2 = new Task3dot5.Person(2, "Bob", "456 Avenue", "222-2222");
        db.add(person1);
        db.add(person2);
    }

    @Test
    public void nameFindByName() {
        assertThat(db.findByName("Alice")).isEqualTo(Collections.singletonList(person1));
        assertThat(db.findByName("Bob")).isEqualTo(Collections.singletonList(person2));
    }

    @Test
    public void addressFindByAddress() {
        assertThat(db.findByAddress("123 Street")).isEqualTo(Collections.singletonList(person1));
        assertThat(db.findByAddress("456 Avenue")).isEqualTo(Collections.singletonList(person2));
    }

    @Test
    public void phoneFindByPhone() {
        assertThat(db.findByPhone("111-1111")).isEqualTo(Collections.singletonList(person1));
        assertThat(db.findByPhone("222-2222")).isEqualTo(Collections.singletonList(person2));
    }

    @Test
    public void idDelete() {
        db.delete(1);
        assertThat(db.findByName("Alice").isEmpty()).isTrue();
        assertThat(db.findByAddress("123 Street").isEmpty()).isTrue();
        assertThat(db.findByPhone("111-1111").isEmpty()).isTrue();
    }
}
